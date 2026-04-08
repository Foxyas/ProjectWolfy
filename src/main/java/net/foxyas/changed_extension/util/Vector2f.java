package net.foxyas.changed_extension.util;

import net.minecraft.util.Mth;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * To be replaced with joml.Vector2f in newer minecraft versions
 */
@ParametersAreNonnullByDefault
public final class Vector2f {

    public float x;
    public float y;

    public Vector2f() {
    }

    public Vector2f(float x, float y) {
        set(x, y);
    }

    public Vector2f(Vector2f other) {
        set(other);
    }

    public static float distSqr(Vector2f vec, Vector2f other) {
        float dx = vec.x - other.x;
        float dy = vec.y - other.y;
        return dx * dx + dy * dy;
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2f set(Vector2f other) {
        return set(other.x, other.y);
    }

    public Vector2f add(float x, float y) {
        return set(this.x + x, this.y + y);
    }

    public Vector2f add(Vector2f vec) {
        return add(vec.x, vec.y);
    }

    public Vector2f sub(float x, float y) {
        return set(this.x - x, this.y - y);
    }

    public Vector2f sub(Vector2f vec) {
        return sub(vec.x, vec.y);
    }

    public Vector2f mul(float scalar) {
        return set(x * scalar, y * scalar);
    }

    public Vector2f mul(float x, float y) {
        return set(this.x * x, this.y * y);
    }

    public Vector2f mul(Vector2f vec) {
        return mul(vec.x, vec.y);
    }

    public Vector2f div(float scalar) {
        return set(x / scalar, y / scalar);
    }

    public Vector2f div(float x, float y) {
        return set(this.x / x, this.y / y);
    }

    public Vector2f div(Vector2f vec) {
        return div(vec.x, vec.y);
    }

    public Vector2f lerp(float delta, Vector2f other) {
        return set(Mth.lerp(delta, x, other.x), Mth.lerp(delta, y, other.y));
    }

    public Vector2f clamp(float min, float max) {
        return set(Mth.clamp(x, min, max), Mth.clamp(y, min, max));
    }

    public Vector2f clamp(float minX, float maxX, float minY, float maxY) {
        return set(Mth.clamp(x, minX, maxX), Mth.clamp(y, minY, maxY));
    }

    public Vector2f clamp(Vector2f min, Vector2f max) {
        return clamp(min.x, max.x, min.y, max.y);
    }

    public Vector2f cpy() {
        return new Vector2f(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vector2f vector2f)) return false;

        return Float.compare(x, vector2f.x) == 0 && Float.compare(y, vector2f.y) == 0;
    }

    @Override
    public int hashCode() {
        int result = Float.hashCode(x);
        result = 31 * result + Float.hashCode(y);
        return result;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }
}
