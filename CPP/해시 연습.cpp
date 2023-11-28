#include<iostream>
typedef unsigned int ui;

int N = 15;
int M = 4;

char MAP[15][15] =
{
	{ 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, },
	{ 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, },
	{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, },
	{ 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, },
	{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, },
	{ 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, },
	{ 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, },
	{ 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, },
	{ 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, },
	{ 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, },
	{ 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, },
	{ 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, },
	{ 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, },
	{ 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, },
	{ 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, },
};
char piece1[4][4] =
{
	{ 1, 0, 0, 1, },
	{ 0, 1, 0, 0, },
	{ 0, 0, 1, 0, },
	{ 0, 0, 0, 1, },
};
char piece2[4][4] =
{
	{ 1, 1, 0, 1, },
	{ 0, 0, 1, 0, },
	{ 0, 1, 0, 0, },
	{ 1, 0, 0, 0, },
};
char piece3[4][4] =
{
	{ 1, 0, 0, 0, },
	{ 0, 1, 0, 1, },
	{ 0, 0, 1, 0, },
	{ 0, 1, 0, 0, },
};

int compare(char MAP[][15], int sr, int sc, char piece[][4]) {
	ui cast = 0;

	for (int i = 0; i < 4; i++) {
		if (*(ui*)&MAP[sr + i][sc] != *(ui*)&piece[i][0]) return 0;
	}

	//for (register int r = 0; r < M; r++) {
	//	for (register int c = 0; c < M; c++) {
	//		if (piece[r][c] != MAP[sr + r][sc + c]) return 0;
	//	}
	//}

	return 1;
}

int findPiece(char MAP[][15], char piece[][4])
{
	int count = 0;
	for (register int r = 0; r <= N - M; r++) {
		for (register int c = 0; c <= N - M; c++) {
			count += compare(MAP, r, c, piece);
		}
	}
	return count;
}

int main(void)
{
	for (register int tc = 0; tc < 1000000; tc++)
	{
		if (findPiece(MAP, piece1) != 5) printf("wrong!\n");
		if (findPiece(MAP, piece2) != 3) printf("wrong!\n");
		if (findPiece(MAP, piece3) != 3) printf("wrong!\n");
	}

	printf("pass\n");

	return 0;
}