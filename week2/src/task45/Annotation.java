package task45;

public class Annotation {
    private final task45.Figure elem;
    private final String label;

    public Annotation (task45.Figure elem, String text) {
        this.elem = elem;
        this.label = text;
    }

    public task45.Figure getElem() {
        return this.elem;
    }

    public String getLabel() {
        return this.label;
    }

    public String toString() {
        if (elem.type.equals("C"))
            return "C (" + elem.x0 + ", " + elem.y0 + "), " + elem.r + ": " + label;
        else
            return "R (" + elem.x0 + ", " + elem.y0 + "), (" + elem.x1 + ", " + elem.y1 + ")" + ": " + label;
    }


}
