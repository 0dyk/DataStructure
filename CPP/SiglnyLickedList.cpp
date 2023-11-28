#include<iostream>
#define MAX_NODE 101
using namespace std;

struct Node {
	int data;
	Node* next;
};
Node nodes[MAX_NODE];
int node_count = 0;

class SinglyLinkedList {
private: 
	Node HEAD;

	Node* new_node(int data) {
		nodes[node_count].data = data;
		nodes[node_count].next = nullptr;
		return &nodes[node_count++];
	}
public:
	SinglyLinkedList() = default;

	void init() {
		HEAD.next = nullptr;
		node_count = 0;
	}

	void insert(int x) {
		Node* node = new_node(x);
		node->next = HEAD.next;
		HEAD.next = node;
	}

	void remove(int x) {
		Node* ptr = &HEAD;
		while (ptr->next != nullptr && ptr->next->data != x) {
			ptr = ptr->next;
		}

		if (ptr->next != nullptr) {
			ptr->next = ptr->next->next;
		}
	}

	bool find(int x) {
		Node* ptr = HEAD.next;

		while (ptr != nullptr && ptr->data != x) {
			ptr = ptr->next;
		}

		if (ptr != nullptr) cout << "find";
		else cout << "nofind";

		cout << '\n';

		return ptr != nullptr;
	}

	void print() {
		Node* ptr = HEAD.next;
		while (ptr != nullptr) {
			cout << ptr->data;
			if (ptr->next != nullptr) {
				cout << " ";
			}
			ptr = ptr->next;
		}
		cout << '\n';
	}

	Node getHead() {
		return HEAD;
	}
};

// test
int main() {
	SinglyLinkedList sll = {};

	int arr[10] = { 1,2,3,4,5,6,7,8,9,10 };
	sll.init();
	for (int x : arr) {
		sll.insert(x);
	}

	sll.print();

	sll.remove(4);
	sll.remove(2);
	sll.remove(7);

	sll.print();

	sll.find(3);
	sll.find(7);

	sll.print();

	return 0;
}