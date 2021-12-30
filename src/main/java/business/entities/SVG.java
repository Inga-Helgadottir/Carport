package business.entities;

public class SVG {
    StringBuilder svg = new StringBuilder();
    private int x;
    private int y;
    private String viewBox;
    private int width;
    private int height;

    public SVG(int x, int y, String viewBox, int width, int height) {
        this.x = x;
        this.y = y;
        this.viewBox = viewBox;
        this.width = width;
        this.height = height;
        svg.append(String.format(headerTemplate, height, width, viewBox, x, y));

    }

    private final String headerTemplate = "<svg height=\"%d%%\" " +
            "width=\"%d%%\" " +
            "viewBox =\"%s\" " +
            "x=\"%s\" " +
            "y=\"%s\" " +
            " preserverAspectRation=\"xMinYMin\">";
    private final String stolpeTemplate = "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"stroke:black; stroke-width:3; fill:white;  fill-opacity:0.1;\" />";
    private final String rectEmptyTemplate = "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"stroke:black; fill:white; fill-opacity:0.1;stroke-opacity:0.9\" />";
    private final String rectDashTemplate = "<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"stroke:black; fill: none; stroke-width: 5;\"stroke-dasharray=\"10,10\" d=\"M5 40 l215 0\" />";
    private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000\" />";
    private final String defsTemplate = "<defs>\n" +
            "<marker id=\"arrow\" refX=\"5\" refY=\"5\"\n" +
            "markerWidth=\"6\" markerHeight=\"6\"\n" +
            "orient=\"auto-start-reverse\">\n" +
            "<path d=\"M 0 0 L 10 5 L 0 10 z\" />\n" +
            "</marker> </defs>";
    private final String lineDashTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-width: 5;\"stroke-dasharray=\"10,10\" d=\"M5 40 l215 0\" />";
    private final String textTemplate = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(%f)\">%s</text>";
    // private final String slantRectTemplate = //TODO template for tag med hældning (ved ikke om vi kommer til at lave det)
    private final String arrowLineTemplate = "<line x1=\"%f\"  y1=\"%f\" x2=\"%f\" y2=\"%f\" \n" +
            "\tstyle=\"stroke: #000000;\n" +
            "\tmarker-start: url(#arrow);\n" +
            "\tmarker-end: url(#arrow);\"/>";


    public void addStolpe(int x, int y, int width, int height) {
        svg.append(String.format(stolpeTemplate, x, y, width, height));
    }


    public void addrectempty(int x, int y, int width, int height) {
        svg.append(String.format(rectEmptyTemplate, x, y, width, height));
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        svg.append(String.format(lineTemplate, x1, y1, x2, y2));
    }

    public void addDashLine(int x1, int y1, int x2, int y2) {
        svg.append(String.format(lineDashTemplate, x1, y1, x2, y2));
    }

    public void addDashRect(int x, int y, int width, int height) {
        svg.append(String.format(rectDashTemplate, x, y, width, height));
    }

    public void addArrowLine(double x1, double y1, double x2, double y2) { //mangler arrow integration
        svg.append(String.format(arrowLineTemplate, x1, y1, x2, y2));
    }

    public void addDefs() {
        svg.append(defsTemplate);
    }

    public void addText() {
        svg.append(textTemplate);
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>";
    }
}
