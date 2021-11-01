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

    public String toString(){
        return this.getElem().toString() + label;
    }

}
