#include <iostream>
#include <fstream>
#include <cmath>
#include <algorithm>
#include <vector>
using namespace std;

int n;
vector<pair<int, int>> coord;
int dist(pair<int, int> a, pair<int, int> b);
int closest_pair(int s, int e);
bool compare_by_y(pair<int, int> a, pair<int, int> b);
void read_file();

int main() {
	read_file();
	sort(coord.begin(), coord.end());
	cout<< closest_pair(0, coord.size()-1);
	return 0;
}
int dist(pair<int, int> a, pair<int, int> b) {
	return pow(a.first - b.first, 2) + pow(a.second - b.second, 2);
}
int closest_pair(int s, int e) {
	if (s + 1 == e) return dist(coord[s], coord[e]);
	if (s + 2 == e) return min({ dist(coord[s], coord[s + 1]), dist(coord[s + 1], coord[s + 2]), dist(coord[s], coord[e]) });
	int mid = (s + e) / 2;
	int min_dist = min(closest_pair(s, mid), closest_pair(mid + 1, e));

	vector<pair<int, int>> coords_in_line;
	coords_in_line.reserve(e - s + 1);
	int line = (coord[mid].first + coord[mid + 1].first) / 2;
	for (int i = s; i <= e; i++) {
		if (pow(line - coord[i].first, 2) < min_dist) {
			coords_in_line.push_back(coord[i]);
		}
	}
	sort(coords_in_line.begin(), coords_in_line.end(), compare_by_y);
	int len = coords_in_line.size();
	for (int i = 0; i < len; i++) {
		for (int j = i + 1; j < len; j++) {
			if (pow(coords_in_line[i].second - coords_in_line[j].second, 2) >= min_dist) break;
			else min_dist = min(min_dist, dist(coords_in_line[i], coords_in_line[j]));
		}
	}
	return min_dist;
}
bool compare_by_y(pair<int, int> a, pair<int, int> b) {
	if (a.second == b.second) return a.first < b.first;
	return a.second < b.second;
}
void read_file() {
	cin >> n;
	while (n--) {
		int x, y;
		cin >> x >> y;
		coord.push_back(make_pair(x, y));
	}
}