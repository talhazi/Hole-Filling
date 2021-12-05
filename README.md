# Hole Filling Algorithm

This library fills holes in images.

The flow of the code is as follows:

1. cmdUtil accepts main-image, mask-image, z, E and connectivity_type as command line arguments.
   Then it used the holeFiller library to fill hole in the image, by:
2. Convert the main image to grayscale normalized and overlay the mask into 2D values map. So the mask pixels values will be -1, and the others will be in range [0,1], as follows: <br/>
   ![holed-image-map]
3. Get all hole pixels.
4. Get all boundary pixels according to the connectivity_type.
5. Fill the whole hole according the formula (see assumptions) for each hole pixel: <br/>
   ![filled-image-map]
6. Convert (write) the new map result into jpg file and save it in the main folder.


### image and mask inputs *example*: <br/>
![Image Example][image-example]
![Image Mask Example][image-mask-example] <br/>
### output *example*: <br/>
![Image Filled Example][image-filled-example]


## Assumptions
* image and image_mask as the same height and width.
* RGB to Grayscale according to the [Average Method](https://www.dynamsoft.com/blog/insights/image-processing/image-processing-101-color-space-conversion/).
* The pixels from the mask will be considered as a hole pixel - if the pixel has grayscale normalized color which is less than 0.5.
* Every image has only a single hole, which is not close to the borders of the image.
* Get hole boundary according to [4-connected and 8-connected](http://en.wikipedia.org/wiki/Pixel_connectivity).
* The formula for calculating hole pixel color as follows: <br/>
  ![fill-hole-formula]
* Default values for z, E, connectivity_type are 3, 0.001, 8 respectively.

## Tests
Few image files for example and testing are in ./tests folder.

## Questions
PDF answers file for the questions is in the main folder.


## Running The Project
1. Download the Hole_Filling zip folder.
2. Extract the Files from the downloaded file.
3. Open the folder [as IntelliJ IDEA project](https://www.jetbrains.com/help/idea/import-project-or-module-wizard.html).
4. (Usage) Add 2 or 5 command line arguments in the configuration as follows: <br/>
   ```
   required: <image> <image_mask>  optional: <z> <E> <connectivity_type>
    ```
5. Examples for command line arguments configuration: <br/>
    ```
    tests/test1.png tests/test1_mask.png 3 0.001 8
    ```
    ```
    tests/test2.png tests/test2_mask.png
    ```
6. RUN.


## Dependencies
* [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html)


## Contact

Tal Hazi <> [talhazi114@gmail.com](mailto:talhazi114@gmail.com)




[image-example]: ./tests/test1.png
[image-mask-example]: ./tests/test1_mask.png
[image-filled-example]: ./tests/output.jpg
[filled-image-map]: ./images/filledImage.png
[holed-image-map]: ./images/holedImage.png
[fill-hole-formula]: ./images/fillHoleFormula.png

