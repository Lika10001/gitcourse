# gitcourse
#include<iostream>
using namespace std;
int main()
{
    setlocale(0, "");
    int rows;
    bool isInCorrect;
    cout << "Построить n строк треугольника Паскаля.\n";
    cout << "Введите количество строк: \n";
    do {
        isInCorrect = false;
        cin >> rows;
        if ((isInCorrect) && (( rows > 2147483647) || (rows < 0))) {
            cout << "Введите число от 0 до 2147483647";
            isInCorrect = true;
        }
    } while (isInCorrect);
    for (int i = 0; i < rows; i++)
    {
        int val = 1;
        for (int j = 1; j < (rows - i); j++)
        {
            cout << "  ";
        }
        for (int k = 0; k <= i; k++)
        {
            cout << "   " << val;
            val = val * (i - k) / (k + 1);
        }
        cout << endl << endl;
    }
    cout << endl;
    return 0;
}
