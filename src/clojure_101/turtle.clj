(ns clojure-101.turtle
  "This is a playground namespace for drawing various shapes using clojure-turtle."
  (:refer-clojure :exclude [repeat])
  (require [clojure-turtle.core :as t :refer :all]))

;; defines the turtle window.
(defonce turtle-window
  (new-window {:size [300 480]}))

;; Things you can tell the turtle to do

(forward 30)

;; Turn right 90 degrees
(right 90)

;; Move backwards
(back 30)

;; Turn left by 90 degrees
(left 90)

;; Set the heading to 270 degrees (down)
(setheading 270)

;; clear all output
(clean)

;; Move the turtle back to its home and change heading back to up
(home)

;; Move the turtle to a location
(setxy 0 0)

;; Make the pen up so movement won't draw
(penup)

;; Put the pen down so movement will draw
(pendown)

;; Sets the color.
;; red, green, blue[, alpha]
;; This is a translucent blue color
(color [0 0 255 30])

;; Starts filling in a shape.
;; Call this then draw lines with the turtle.
(start-fill)

;; End fill
;; This will fill in the shape that was drawn
(end-fill)



(defn draw-circle
  "Draws a filled in circle with the specified number of sides and radius. The
   circle will be filled in with the current color."
  [num-sides radius]
  (let [circumference (* Math/PI 2.0 radius)
        forward-move-amt (/ circumference num-sides)
        angle-to-turn (/ 360.0 num-sides)]
    (start-fill)
    (dotimes [n num-sides]
      (right angle-to-turn)
      (forward forward-move-amt))
    (end-fill)))

(draw-circle 40 50)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Challenges

;; 1. Draw a triangle

;; 2. Define a function for drawing a triangle

;; 3. Add an argument to the function to scale the triangle

;; 4.  Define a function for drawing a square

;; 5. Combine the functions together and draw a house.


;; 6. Draw a flower
;; - Define a function to draw a petal
;; - Draw multiple petals at differnt angles
;; - Draw the center of the flower.
;; - Draw the stem and leaves
