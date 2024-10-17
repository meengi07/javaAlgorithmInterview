

public class CustomHashMap {

    public class Node {
        int key;
        int value;
    }

    private Node[] nodes = new Node[10000];

    public void put(int key, int value) {
        int index = key % nodes.length;

        if(nodes[index] == null) {
            nodes[index] = new Node(key, value);
            return;
        }

        Node node = nodes[index];
        while(node != null) {
            if(node. == key) {
                node.value = value;
                return;
            }
            node = node.next;
        }
    }

}