package com.radityalabs.android.corona.features.main.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.expressions.Expression.*
import com.mapbox.mapboxsdk.style.layers.CircleLayer
import com.mapbox.mapboxsdk.style.layers.HeatmapLayer
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.radityalabs.android.corona.R
import com.radityalabs.android.corona.features.main.presentation.sheet.BottomSheetFragment
import java.net.URI
import java.net.URISyntaxException


private const val EARTHQUAKE_SOURCE_URL =
    "https://www.mapbox.com/mapbox-gl-js/assets/earthquakes.geojson"
private const val HEATMAP_LAYER_ID = "earthquakes-heat"
private const val HEATMAP_LAYER_SOURCE = "earthquakes"
private const val EARTHQUAKE_SOURCE_ID = "earthquakes"
private const val CIRCLE_LAYER_ID = "earthquakes-circle"

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mapView: MapView
    private lateinit var mapBox: MapboxMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomSheetFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initView(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    private fun initView(view: View, savedInstanceState: Bundle?) {
        mapView = view.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapBox ->
            mapBox.setStyle(Style.DARK) { style ->
                addEarthquakeSource(style);
                addHeatmapLayer(style);
                addCircleLayer(style);
            }
        }
        mapView
    }

    private fun addEarthquakeSource(loadedMapStyle: Style) {
        try {
            loadedMapStyle.addSource(
                GeoJsonSource(
                    EARTHQUAKE_SOURCE_ID,
                    URI(EARTHQUAKE_SOURCE_URL)
                )
            )
        } catch (uriSyntaxException: URISyntaxException) {

        }
    }

    private fun addHeatmapLayer(style: Style) {
        val layer = HeatmapLayer(HEATMAP_LAYER_ID, EARTHQUAKE_SOURCE_ID)
        layer.maxZoom = 9f
        layer.setSourceLayer(HEATMAP_LAYER_SOURCE)
        layer.setProperties( // Color ramp for heatmap.  Domain is 0 (low) to 1 (high).
            // Begin color ramp at 0-stop with a 0-transparency color
            // to create a blur-like effect.
            heatmapColor(
                interpolate(
                    linear(), heatmapDensity(),
                    literal(0), rgba(33, 102, 172, 0),
                    literal(0.2), rgb(103, 169, 207),
                    literal(0.4), rgb(209, 229, 240),
                    literal(0.6), rgb(253, 219, 199),
                    literal(0.8), rgb(239, 138, 98),
                    literal(1), rgb(178, 24, 43)
                )
            ),  // Increase the heatmap weight based on frequency and property magnitude
            heatmapWeight(
                interpolate(
                    linear(), get("mag"),
                    stop(0, 0),
                    stop(6, 1)
                )
            ),  // Increase the heatmap color weight weight by zoom level
            // heatmap-intensity is a multiplier on top of heatmap-weight
            heatmapIntensity(
                interpolate(
                    linear(), zoom(),
                    stop(0, 1),
                    stop(9, 3)
                )
            ),  // Adjust the heatmap radius by zoom level
            heatmapRadius(
                interpolate(
                    linear(), zoom(),
                    stop(0, 2),
                    stop(9, 20)
                )
            ),  // Transition from heatmap to circle layer by zoom level
            heatmapOpacity(
                interpolate(
                    linear(), zoom(),
                    stop(7, 1),
                    stop(9, 0)
                )
            )
        )

        style.addLayerAbove(layer, "waterway-label");
    }

    private fun addCircleLayer(loadedMapStyle: Style) {
        val circleLayer = CircleLayer(CIRCLE_LAYER_ID, EARTHQUAKE_SOURCE_ID)
        circleLayer.setProperties( // Size circle radius by earthquake magnitude and zoom level
            circleRadius(
                interpolate(
                    linear(), zoom(),
                    literal(7), interpolate(
                        linear(), get("mag"),
                        stop(1, 1),
                        stop(6, 4)
                    ),
                    literal(16), interpolate(
                        linear(), get("mag"),
                        stop(1, 5),
                        stop(6, 50)
                    )
                )
            ),  // Color circle by earthquake magnitude
            circleColor(
                interpolate(
                    linear(), get("mag"),
                    literal(1), rgba(33, 102, 172, 0),
                    literal(2), rgb(103, 169, 207),
                    literal(3), rgb(209, 229, 240),
                    literal(4), rgb(253, 219, 199),
                    literal(5), rgb(239, 138, 98),
                    literal(6), rgb(178, 24, 43)
                )
            ),  // Transition from heatmap to circle layer by zoom level
            circleOpacity(
                interpolate(
                    linear(), zoom(),
                    stop(7, 0),
                    stop(8, 1)
                )
            ),
            circleStrokeColor("white"),
            circleStrokeWidth(1.0f)
        )
        loadedMapStyle.addLayerBelow(circleLayer, HEATMAP_LAYER_ID)
    }

    private fun setupBottomSheetFragment() {
        val fragment = BottomSheetFragment.newInstance()
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_bottom_sheet, fragment, "hello")
            .disallowAddToBackStack()
            .commit()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}