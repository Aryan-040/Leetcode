/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // Map to keep track of the link between original nodes and cloned nodes 🗺️
        Map<Node, Node> hashMap = new HashMap<>();
        Node curr = head;

        // Step 1: Create deep-copied shells of all nodes and catalog them 🖨️
        while (curr != null) {
            hashMap.put(curr, new Node(curr.val));
            curr = curr.next; 
        }

        curr = head;
        // Step 2: Loop back through to wire up next and random pointers securely 🪡
        while (curr != null) {
            Node cp = hashMap.get(curr);
            
            // Set pointers using mapped values
            cp.next = hashMap.get(curr.next);
            cp.random = hashMap.get(curr.random);
            
            curr = curr.next;
        } 
        
        // Return the cloned head node
        return hashMap.get(head);
    }
}