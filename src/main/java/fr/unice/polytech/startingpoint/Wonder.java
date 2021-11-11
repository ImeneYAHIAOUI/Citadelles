package proposition1;

public class Wonder implements IWonder {
    private int val;
    private Color color;
    private String name;
    private String description;

    public Wonder(int val, Color color, String name, String description) {
        this.val = val;
        this.color = color;
        this.name = name;
        this.description = description;
    }

    @Override
    public void doAction() {
        System.out.println("Do something");
    }

    @Override
    public void effectOfAction() {
        System.out.println("Effect is ...");
    }

    @Override
    public int getVal() {
        return this.val;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean isWonder() {
        return true;
    }
}
