(ns clojure-101.turtle
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
  [num-sides radius]
  (let [circumference (* Math/PI 2.0 radius)
        forward-move-amt (/ circumference num-sides)
        angle-to-turn (/ 360.0 num-sides)]
    (start-fill)
    (dotimes [n num-sides]
      (t/right angle-to-turn)
      (t/forward forward-move-amt))
    (end-fill)))

(draw-circle 40 50)
