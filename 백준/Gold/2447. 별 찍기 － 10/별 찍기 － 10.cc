//
// Created by 김균호 on 2025. 6. 5..
//
#include <iostream>
using namespace std;

bool isBlank(int i, int j) {
	while (i > 0 && j > 0) {
		if (i % 3 == 1 && j % 3 == 1) return true;
		i /= 3;
		j /= 3;
	}
	return false;
}

int main() {
	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (isBlank(i, j))
				cout << ' ';
			else
				cout << '*';
		}
		cout << '\n';
	}
}
