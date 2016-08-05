import java.util.ArrayList;
import java.util.List;

public class TableCalculator {
  private List<Integer> mNumbersOfTable;
  private List<Integer> mNextNumbers;

  private int nextNumbersIterator = 0;
  private int previousNumber;
  private int currentNumber = 0;

  public TableCalculator() {
    mNumbersOfTable = new ArrayList<>();
    mNextNumbers = new ArrayList<>();
  }

  public void init(int tableNumber) {
    mNumbersOfTable.add(tableNumber);
    previousNumber = currentNumber;
    currentNumber = tableNumber;
    if (isFirstElement()) {
      return;
    }
    int difference = currentNumber - previousNumber;
    if (hasLostElement(difference)) {
     calculateNextNumber(difference);
    }
  }

  private boolean isFirstElement() {
    return previousNumber == 0;
  }

  private boolean hasLostElement(int difference) {
    return difference > 1;
  }

  private void calculateNextNumber(int difference) {
    int startNumber = previousNumber + 1;
    for (int nextNumber = startNumber; nextNumber < previousNumber + difference; nextNumber++) {
      mNextNumbers.add(nextNumber);
    }
  }

  public void add(int tableNumber) {
    int index = tableNumber - 1;
    mNumbersOfTable.add(index, tableNumber);
    if (isSuccesfulRemove(tableNumber)) {
      refreshIterator();
    }
  }

  private boolean isSuccesfulRemove(int tableNumber) {
    return mNextNumbers.remove((Integer) tableNumber);
  }

  public int getNextNumber() {
    if (isLostValuesEnded()) {
      return ++currentNumber;
    }
    int next = mNextNumbers.get(nextNumbersIterator);
    nextNumbersIterator++;
    return next;
  }

  private boolean isLostValuesEnded() {
    return nextNumbersIterator == mNextNumbers.size();
  }

  public void remove(int tableNumber) {
    removeTableNumber(tableNumber);
    refreshIterator();
    for (int i = 0; i < mNextNumbers.size(); i++) {
      if (isTableNumberPosition(i, tableNumber)) {
        mNextNumbers.add(i, tableNumber);
        break;
      }
    }
  }

  private void removeTableNumber(int tableNumber) {
    mNumbersOfTable.remove((Integer) tableNumber);
  }

  private boolean isTableNumberPosition(int iterator, int tableNumber) {
    return mNextNumbers.get(iterator) > tableNumber;
  }

  private void refreshIterator() {
    nextNumbersIterator = 0;
  }

}
