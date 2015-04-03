import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

def phototest(img: BufferedImage): BufferedImage = {
  // obtain width and height of image
  val w = img.getWidth
  val h = img.getHeight

  // create new image of the same size
  val out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)

  // copy pixels (mirror horizontally)
  for (x <- 0 until w)
    for (y <- 0 until h)
      out.setRGB(x, y, img.getRGB(w - x - 1, y) & 0xffffff)
  
  // draw red diagonal line
  for (x <- 0 until (h min w))
    out.setRGB(x, x, 0xff0000)

  out
}
  
def test() {
  // read original image, and obtain width and height
  val photo1 = ImageIO.read(new File("photo.jpg"))
  
  val photo2 = phototest(photo1) 

  // save image to file "test.jpg"
  ImageIO.write(photo2, "jpg", new File("test.jpg"))
}

test()
