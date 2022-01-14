package ExtraClasses;
class Node {
    int value;
    Node leftPair;
    Node rightPair;

    Node(int value) {
        this.value = value;
        rightPair = null;
        leftPair = null;
    }
    Node() {
        rightPair = null;
        leftPair = null;
        value = -1;
    }
}