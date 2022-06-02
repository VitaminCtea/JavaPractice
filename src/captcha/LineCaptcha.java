package captcha;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineCaptcha extends Captcha implements Captcha.ILineCaptcha {
    private record RecordLetter(String letter, int afterRotateLetterWidth, double radian) {}
    public LineCaptcha(int width, int height) { super(width, height); }
    public LineCaptcha(int width, int height, int letters) { super(width, height, letters); }
    public LineCaptcha(int width, int height, String code) { super(width, height, code); }

    @Override public byte[] generateVerificationCodeImage() { return generateVerificationCodeImage(getFileType(), randomCreated, verificationCode, code); }
    @Override public byte[] generateVerificationCodeImage(String fileType, boolean useDefaultVerificationCode, StringBuilder verificationCode, String code) {
        try {
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bufferedImage.createGraphics();

            makeImageHighFidelityQuality(g);
            generateRoundedCanvas(g, bufferedImage);

            generateHotPixel(g);
            generateInterferenceLine(g);
            generateVerificationCode(g, useDefaultVerificationCode, verificationCode, code);
            g.dispose();

            return saveImageByteArray(bufferedImage, fileType);
        } catch (IOException e) { e.printStackTrace(); }

        return new byte[0];
    }

    private void makeImageHighFidelityQuality(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void generateRoundedCanvas(Graphics2D g, BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        g.fillRoundRect(0, 0, width, height, 20, 20);
        g.setComposite(AlphaComposite.SrcIn);
        g.drawImage(bufferedImage, 0, 0, width, height, null);
    }

    private void generateHotPixel(Graphics2D g) {
        for (int i = 0; i < width * 2; i++) {
            g.setPaint(generateRandomColor());
            g.fillOval(rand.nextInt(width), rand.nextInt(height), 2, 2);
        }
    }

    private void generateInterferenceLine(Graphics2D g) {
        for (int i = 0; i < interfereCount; i++) {
            g.setPaint(generateRandomColor());
            g.drawLine(rand.nextInt(width), rand.nextInt(height), rand.nextInt(width), rand.nextInt(height));
        }
    }

    private void generateVerificationCode(Graphics2D g, boolean useDefaultVerificationCode, StringBuilder verificationCode, String code) {
        g.setFont(font);

        List<RecordLetter> recordLetters = recordLetterAttribute(g, useDefaultVerificationCode, verificationCode, code);
        int letterTotalWidth = recordLetters.stream().mapToInt(RecordLetter::afterRotateLetterWidth).sum();
        int remainingSpace = width - letterTotalWidth;
        int letterSpace = remainingSpace / 3 / code.length();

        int startLetterXPosition = (width - (letterTotalWidth + letterSpace * (letters - 1))) / 2;
        int endLetterYPosition = height - font.getSize() / 2;
        int length = Math.min(code.length(), letters);
        for (int i = 0; i < length; i++) {
            RecordLetter letter = recordLetters.get(i);
            g.setPaint(generateRandomColor());

            AffineTransform affineTransform =
                    AffineTransform.getRotateInstance(
                            letter.radian(),
                            startLetterXPosition + letter.afterRotateLetterWidth() / 2.0,
                            height / 2.0
                    );
            g.setTransform(affineTransform);

            g.drawString(letter.letter(), startLetterXPosition, endLetterYPosition);
            startLetterXPosition += letter.afterRotateLetterWidth() + letterSpace;
        }
    }

    private List<RecordLetter> recordLetterAttribute(Graphics2D g, boolean useDefaultVerificationCode, StringBuilder verificationCode, String code) {
        if (verificationCode.length() >= letters) verificationCode.delete(0, letters);
        List<RecordLetter> recordLetters = new ArrayList<>();
        FontMetrics fontMetrics = g.getFontMetrics(font);
        int textHeight = fontMetrics.getHeight();
        int length = Math.min(code.length(), letters);
        for (int i = 0; i < length; i++) {
            String letter = String.valueOf(code.charAt(useDefaultVerificationCode ? rand.nextInt(code.length()) : i));
            double radian = Math.PI / 5 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1);
            int textWidth = fontMetrics.stringWidth(letter);
            int afterRotateWidth = (int) Math.sqrt(textWidth * textWidth + textHeight * textHeight) / 2;
            verificationCode.append(letter);
            recordLetters.add(new RecordLetter(letter, afterRotateWidth, radian));
        }

        return recordLetters;
    }
}
