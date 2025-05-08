package io.github.CR.PlagueRats.GUI_thaddeus.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
/**
 * CameraWrapper
 * ->
 * Utility to convert screen‐space coordinates into world‐space,
 * wrapping LibGDX’s OrthographicCamera:
 *   • unproject(screenX, screenY) → Vector2(worldX, worldY)
 *   • expose the raw camera for advanced uses
 */
public class CameraWrapper {
    private final OrthographicCamera camera;

    /** Wrap an existing OrthographicCamera */
    public CameraWrapper(OrthographicCamera camera) {
        this.camera = camera;
    }

    /**
     * Convert screen coords (px) to world coords (units).
     * @param screenX x in pixels
     * @param screenY y in pixels
     * @return world‐space Vector2
     */
    public Vector2 unproject(int screenX, int screenY) {
        // LibGDX returns a Vector3; we discard z
        Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        return new Vector2(worldCoords.x, worldCoords.y);
    }

    /** Direct access to the underlying camera (for viewport updates) */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /** Update the camera’s matrices (must call before rendering) */
    public void update() {
        camera.update();
    }
}
/*
 * Patterns:
 *   • Adapter     ◀ Structural (adapts OrthographicCamera to a simpler unproject API)
 */
