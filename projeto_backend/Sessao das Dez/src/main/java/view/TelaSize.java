package view;

import java.awt.*;

public enum TelaSize {
    SMALL(800, 600),
    PADRAO(1280,
            720);

    private final int width;
        private final int height;


        TelaSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }



    }


