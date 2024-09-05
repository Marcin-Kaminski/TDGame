package helperMethods;

public class Constants {

    public static class Direction {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Tiles {
        public static final int WATER_TILE = 0;
        public static final int GRASS_TILE = 1;
        public static final int ROAD_TILE = 2;
    }

    public static class Enemies {
        public static final int ORC = 0;
        public static final int BAT = 1;
        public static final int KNIGHT = 2;
        public static final int WOLF = 3;

        public static float GetSpeed(int enemyType) {
            return switch (enemyType) {
                case ORC -> 0.5f;
                case BAT -> 0.65f;
                case KNIGHT -> 0.3f;
                case WOLF -> 0.75f;
                default -> 0;
            };

        }
    }

    public static class Towers {
        public static final int CANNON = 0;
        public static final int ARCHER = 1;
        public static final int WIZARD = 2;

        public static String getName(int towerType) {
            return switch (towerType) {
                case CANNON -> "Cannon";
                case ARCHER -> "Archer";
                case WIZARD -> "Wizard";
                default -> "";
            };

        }
    }
}
