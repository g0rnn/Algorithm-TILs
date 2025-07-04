import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Node> topNodes = new TreeMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());

            String top = st.nextToken();
            Node parent = topNodes.computeIfAbsent(top, Node::new);

            for (int j = 1; j < k; j++) {
                String fruit = st.nextToken();
                parent = parent.children.computeIfAbsent(fruit, Node::new);
            }
        }
        br.close();

        for (Map.Entry<String, Node> entry : topNodes.entrySet()) {
            printNode(entry.getValue(), 0);
        }
    }

    private static void printNode(Node parent, int depth) {
        String prefix = "-".repeat(2 * depth);
        System.out.println(prefix + parent.fruit);

        for (Map.Entry<String, Node> entry : parent.children.entrySet()) {
            printNode(entry.getValue(), depth + 1);
        }
    }

    static class Node{
        String fruit;
        Map<String, Node> children = new TreeMap<>();

        Node(String f) {
            this.fruit = f;
        }
    }
}
