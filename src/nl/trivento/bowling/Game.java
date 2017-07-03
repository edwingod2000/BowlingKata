package nl.trivento.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
  private Random random = new Random();
  private List<Frame> frames = new ArrayList<>();

  public static void main(String[] args) {
    new Game();
  }

  public Game() {
    for (int i=0; i <= 9; i++) {
      // Frames aanmaken
      Frame frame = new Frame(i+1);
      frames.add(frame);
      // Volgend frame koppelen
      if (i > 0) {
        frames.get(i-1).addNextFrame(frame);
      }

      // gooien
      frames.get(i).setFirstRoll(randomPins(10));
      if (!frames.get(i).isStrike()) {
        frames.get(i).setSecondRoll(randomPins(10 - frames.get(i).getFirstRoll()));
      }

      // 10e frame
      if (frames.get(i).getFrameNumber() == 10) {
        if (frames.get(i).isStrike()) {
          frames.get(i).setSecondRoll(randomPins(10));
          if (frames.get(i).getSecondRoll() == 10) {
            frames.get(i).setExtraRoll(randomPins(10));
          } else {
            frames.get(i).setExtraRoll(randomPins(10 - frames.get(i).getSecondRoll()));
          }
        } else if (frames.get(i).isSpare()) {
          frames.get(i).setExtraRoll(randomPins(10));
        }
      }
    }
    print();
  }

  private int score() {
    int totalScore = 0;
    for (Frame frame: frames) {
      totalScore += frame.score();
    }
    return totalScore;
  }

  private int randomPins(int maxPins) {
    return random.nextInt(maxPins + 1);
  }

  public List<Frame> getFrames() {
    return frames;
  }

  public void print() {
    OutputUtil.printGame(this);
  }

}
