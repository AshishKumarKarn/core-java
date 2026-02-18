package karn.core.multithreading.multithreading;

public class RecursiveFinalReferences {


    //this example is to show that final references can be recursive and still be safely published
    //for single threaded access, this is not a problem,
    // but for multi-threaded access, it is important to ensure that the final reference is properly published to other threads to avoid visibility issues.
    static class Tree {
        final Leaf leaf;

        Tree() {
            leaf = new Leaf(this);
        }

        class Leaf {
            final Tree tree;

            public Leaf(Tree tree) {
                this.tree = tree;
            }
        }
    }

}
