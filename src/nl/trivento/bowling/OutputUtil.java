package nl.trivento.bowling;

public class OutputUtil {

  public static void printGame(Game game) {
    String[] scoreSheet = initialiseScoreSheet();
    int subTotalScore = 0;
    for (Frame frame: game.getFrames()) {
      String[] framePrint = frame.print(subTotalScore);
      subTotalScore += frame.score();

      for (int j = 0; j < framePrint.length; j++) {
        scoreSheet[j] += framePrint[j];
      }
    }

    printScoreSheet(scoreSheet);
  }

  private static String[] initialiseScoreSheet() {
    String[] scoreSheet = new String[6];

    for (int i = 0; i < scoreSheet.length; i++) {
      scoreSheet[i] = "";
    }

    return scoreSheet;
  }

  private static void printScoreSheet(String[] scoreSheet) {
    for (String scoreSheetLine: scoreSheet) {
      System.out.println(scoreSheetLine);
    }
  }

  public static String[] printFrame(Frame frame, int subTotalScore) {
    String[] printResult = new String[6];
    printResult[0] = "________";

    if (frame.getFrameNumber() == 10) {
      printResult[1] = "|__" + frame.getFrameNumber() + "__|";
    } else {
      printResult[1] = "|___" + frame.getFrameNumber() + "__|";
    }

    String roll1 = " ";
    String roll2 = " ";

    if (frame.isStrike()) {
      if (frame.getFrameNumber() == 10) {
        roll1 = "X";
        roll2 = ""+frame.getSecondRoll();
      } else {
        roll2 = "X";
      }
    } else if (frame.isSpare()) {
      roll1 = ""+frame.getFirstRoll();
      roll2 = "/";
    } else {
      roll1 = ""+frame.getFirstRoll();
      roll2 = ""+frame.getSecondRoll();
    }
    if (frame.getExtraRoll() == null) {
      printResult[2] = "|   " + roll1 + "|" + roll2 + "|";
    } else {
      if (frame.getSecondRoll() == 10) {
        roll2 = "X";
      }
      String extraRoll;
      if (frame.getExtraRoll() == 10) {
        extraRoll = "X";
      } else {
        extraRoll = ""+frame.getExtraRoll();
      }
      printResult[2] = "| " + roll1 + "|" + roll2 + "|" + extraRoll + "|";
    }

    printResult[3] = "|------|";

    if (frame.score() + subTotalScore > 99) {
      printResult[4] = "| " + (frame.score() + subTotalScore) + "  |";
    } else  if (frame.score() + subTotalScore > 9) {
      printResult[4] = "|  " + (frame.score() + subTotalScore) + "  |";
    } else {
      printResult[4] = "|   " + (frame.score() + subTotalScore) + "  |";
    }
    printResult[5] = "|______|";

    return printResult;
  }
}
