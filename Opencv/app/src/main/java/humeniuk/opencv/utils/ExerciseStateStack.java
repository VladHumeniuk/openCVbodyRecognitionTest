package humeniuk.opencv.utils;

import java.util.ArrayList;
import java.util.List;

import humeniuk.opencv.model.ExerciseState;
import humeniuk.opencv.model.Position;

public class ExerciseStateStack {

    private List<ExerciseState> stack;

    public ExerciseStateStack() {
        stack = new ArrayList<>();
    }

    public void pushState(ExerciseState state) {
        stack.add(state);
    }

    public ExerciseState peekFromEnd(int offset) {
        if (stack.size() < offset + 1) {
            return new ExerciseState(0, Position.NULL);
        } else {
            return stack.get(stack.size() - offset - 1);
        }
    }

    public ExerciseState peek() {
        return stack.get(stack.size() - 1);
    }

    public ExerciseState pop() {
        ExerciseState last = peek();
        stack.remove(stack.size() - 1);
        return last;
    }

    public int getSize() {
        return stack.size();
    }

    public void remove(int count) {
        for (int i = 0; i < count; i++) {
            pop();
        }
    }
}
