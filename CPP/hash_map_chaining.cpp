#include<iostream>
#include<cstring>

#define MAX_N 1000
#define MAX_LEN 5

unsigned int djb2(char* str) {
	unsigned int hash = 5381;
	for (; *str; ++str) {
		hash = ((hash << 5) + hash) + *str;
	}
	return hash;
}

struct Node {
	char str[MAX_LEN + 1];
	int data;
	Node* next;
}nodes[MAX_N];
int node_count = 0;

Node* new_node(char str[MAX_LEN + 1], int data) {
	std::strcpy(nodes[node_count].str, str);
	nodes[node_count].data = data;
	nodes[node_count].next = nullptr;

	return &nodes[node_count++];
}


class HashMap {
	static constexpr unsigned int TABLE_SIZE = 1 << 12;
	static constexpr unsigned int DIV = TABLE_SIZE - 1;

	Node hash_table[TABLE_SIZE];

public:
	HashMap() = default;

	void init() {
		memset(hash_table, 0, sizeof hash_table);
		node_count = 0;
	}

	void insert(char str[MAX_LEN + 1], int data) {
		Node* prev_node = get_prev_node(str);
		if (prev_node->next == nullptr) {
			prev_node->next = new_node(str, data);
		}
		else {
			prev_node->next->data = data;
		}
	}

	void remove(char str[MAX_LEN + 1]) {
		Node* prev_node = get_prev_node(str);
		if (prev_node->next != nullptr) {
			prev_node->next = prev_node->next->next;
		}
	}

	Node* get(char str[MAX_LEN + 1]) {
		return get_prev_node(str)->next;
	}

private:
	Node* get_prev_node(char str[MAX_LEN + 1]) {
		Node* prev_ptr = &hash_table[djb2(str) & DIV];
		while (prev_ptr->next != nullptr && std::strcmp(prev_ptr->next->str, str) != 0) {
			prev_ptr = prev_ptr->next;
		}

		return prev_ptr;
	}
};


int main() {
	HashMap hm{};

	char str[MAX_LEN + 1];
	Node* ptr;
	
	char ss[5] = { 'a', 'p', 'p', 'l','e' };

	hm.insert(ss, 2);

	hm.insert(str, 3);
	hm.insert(str, 4);
	hm.insert(str, 5);

	hm.get(str);

	hm.remove(str);

	hm.get(str);

	return 0;
}