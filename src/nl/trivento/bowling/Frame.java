package nl.trivento.bowling;

public class Frame {
  private int frameNumber;
  private int firstRoll = 0;
  private int secondRoll = 0;
  private Integer extraRoll;
  private Frame nextFrame;

  public Frame(int frameNumber) {
    this.frameNumber = frameNumber;
  }

  public int score() {
    int score;
    if (isStrike()) {
      if (frameNumber != 10) {
        if (nextFrame.isStrike()) {
          score = firstRoll + nextFrame.getFirstRoll() + nextFrame.getNextFrame().getFirstRoll();
        } else {
          score = firstRoll + nextFrame.getTotalPins();
        }
      } else {
        score = getTotalPins() + extraRoll;
      }
    } else if (isSpare()) {
      if (frameNumber != 10) {
        score = getTotalPins() + nextFrame.getFirstRoll();
      } else {
        score = getTotalPins() + extraRoll;
      }
    } else {
      score = getTotalPins();
    }
    return score;
  }

  public int getFrameNumber() {
    return frameNumber;
  }

  public void addNextFrame(Frame nextFrame) {
    this.nextFrame = nextFrame;
  }

  public boolean isSpare() {
    return (!isStrike() && getTotalPins() == 10);
  }

  public boolean isStrike() {
    return firstRoll == 10;
  }

  public int getTotalPins() {
    return firstRoll + secondRoll;
  }

  public int getFirstRoll() {
    return firstRoll;
  }

  public void setFirstRoll(int firstRoll) {
    this.firstRoll = firstRoll;
  }

  public int getSecondRoll() {
    return secondRoll;
  }

  public void setSecondRoll(int secondRoll) {
    this.secondRoll = secondRoll;
  }

  public void setExtraRoll(int extraRoll) {
    this.extraRoll = extraRoll;
  }

  public Integer getExtraRoll() {
    return extraRoll;
  }

  public Frame getNextFrame() {
    return nextFrame;
  }

  public String[] print(int subTotalScore) {
    return OutputUtil.printFrame(this, subTotalScore);
  }

}
