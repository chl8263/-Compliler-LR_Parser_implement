import java.util.ArrayList;

public class OutPut {
    private String state;
    private String mainStack;

    private String inputStack;
    private String construction;
    private String output;

    public OutPut(String state, String mainStack, String inputStack, String construction, String output) {
        this.state = state;
        this.mainStack = mainStack;
        this.inputStack = inputStack;
        this.construction = construction;
        this.output = output;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMainStack() {
        return mainStack;
    }

    public void setMainStack(String mainStack) {
        this.mainStack = mainStack;
    }

    public String getInputStack() {
        return inputStack;
    }

    public void setInputStack(String inputStack) {
        this.inputStack = inputStack;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
