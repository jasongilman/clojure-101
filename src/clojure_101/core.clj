(ns clojure-101.core
  "This demonstrates some Clojure fundamentals."
  (require [clojure.string :as string]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Clojure Syntax

; This is a comment

(+ 1 2)

(+ 1 2 3 4 5 6 7)

(+ ; <--- function`
  1  ;<-- args
  2)

;; Nested inner forms are evaluated first.
(+ (- 3 2) 1)

; a literal list. (Talk about what quoting does)
'(+ 2 3)


(eval '(+ 2 3))

(comment
 ;; Println for outputting text to the replaced
 (println "Hello world"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Simple Values

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Strings

"Hello, this is a string"

(type "this is a string")

"Strings
can go across multiple
lines"

"And then I said \"No Way!\""

(str "a start " 1 2 3 "other strings")

(format "A string: %s A number: %d"
        "foo" 5)

;; Accessing vars/functions in another namespace

;;clojure.string is a Clojure namespace
;; Syntax for accessing a var in another namespace is:
;; namespace/var-name

;; string is an alias in this namespace for clojure.string. (See top of file.)
(string/join "," [1 2 3])

(clojure.string/join "," [1 2 3])

(comment
 ;; clojure.repl/dir is a helper function for listing the functions in another namespace
 (clojure.repl/dir clojure.string))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Keywords

:a-keyword
:username

(type :keyword)

(keyword "a-string")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Booleans

true

false

nil

;; If

(if true
  ;; true clause
  "true is true"
  ;; false clause
  "The world is broken")

(if false
  "The world is broken"
  "false is false")

(if nil
  "The world is broken"
  "nil is false")

(if "anything else is truthy"
  "Non nil, non-boolean are truthy"
  "The world is broken")

;; A do clause allows you to to force multiple side effects

(comment
 (if true
   (do
    (println "I have multiple side effects")
    (println "So a do clause is necessary")
    "true clause!")
   "The world is broken"))


;; AND and OR

(and true
     "strings"
     1
     :foo
     "the last thing")

(or false
    false
    1
    "Or short circuits")


;; What does this return?

(and (or false nil 2)
     (and 1 2 3)
     (or nil nil 7))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Numbers

;; Integers

1
-672
55039

(type 1)

;; Double aka Floats

1.0
0.576

(type 1.0)

;; Ratios

1/3

-7/4

(type 1/3)


;; Some Math

(+ 1 2 3 4.5 6)

(- 2 1)

(* 2 5)

(/ 1 2)
(/ 1 2.0)

;; Java interop

;; Calling a static method.
(java.lang.Math/pow 5 2)

;; you don't have to specify java.lang items
(Math/pow 5 2)

;; Java instance methods are invoked this way.
(.toString 5)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Data Structures

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Lists

;; Typically lists are only used for representing code

'(1 2 3 4)

(list 1 2 3)

;; Add on to the front
(cons 0 (list 1 2))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vectors

[]
[1 2 3 4]
["this" "is" "a mixed set of items" true 4]

(type [])

(ancestors (type []))

;; vector is a function that takes a variable number of args
(vector 1 2 3 4)

;; Vec will turn a list or sequence into a vector
(vec '(1 2 3))

;; Vectors have O(1) lookup time (actually O(log32(N)))

(nth ["a" "b" "c"] 0)
(nth ["a" "b" "c"] 1)

;; Conj adds to the collection. The addition happens differently depending on the type
(conj [1 2 3] 4) ;; at the end

;; The type is maintained
(conj '(1 2 3) 4)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Maps

{:first-name "Marie", :last-name "Curie"}

{}


;; Keys can be almost anything
{[1 2] "The key was a vector"
 45.7 "The key was a double"}

;; Constructor function from arguments
(hash-map :key "value" :key2 "another value")

;; "Modifying" a map.
(assoc {:foo "bar"} :key "value")

(dissoc {:foo "bar" :a "something"} :foo)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sets

#{1 2 3}

(set [1 2 1 1 1 3 3])

(conj #{1 2 3} 3)
(conj #{1 2 3} "something new")

;; Disj is only for removing items from sets.
(disj #{1 2 3} 2)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Immutability

;; def let's you define a named var in a namespace

(def my-numbers
  [1 2 3 4 5 6 7])

;; We can refer to it later
my-numbers

;; Data is immutable in Clojure. These functions return a new value with the change
;; applied.
(def my-new-numbers
 (conj my-numbers 8))

;; my-numbers hasn't changed
(= my-numbers my-new-numbers)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Functions and Let

;; A function takes arguments, performs some action and then returns a value.

(defn adder
  "This is a doc string. I add two numbers together"

  ;; arguments
  [x y]

  ;; Body (can contain multiple statments.)
  (+ x y))

(adder 1 2)

(comment
 ;; Let gives you name values in a scoped way.
 (let [x 5 ;; names on the left values and code on the right
       y (+ x 1)
       z (* x 2)]
   ;; This is the body
   (println "I'm in the body of a let")

  ;; The last value/result is returned
  [x y z]))

(defn logging-adder
  [x y]
  (println "Adding" x "and" y)
  (let [result (adder x y)]
    (println "The result was" result)
    result))

(comment
 (logging-adder 5000 2))


;; When to use def versus let

(def message
  "Use defs for top level functions and values that don't change.

   let should be used for temporarily setting values in a function.")

;; Anonymous function forms

;; This is an unnamed function
(fn [x y]
  (str "Some values: " x ", " y))


(let [my-temp-function (fn [x y]
                         (str "Some values: " x ", " y))]
  (my-temp-function 3 4))


;; Another way of writing a short one line function
#(str "Some values: " %1 ", " %2)

(let [my-temp-function #(str "Some values: " %1 ", " %2)]
  (my-temp-function 3 4))

;; Single argument function just use %
#(str "I take one argument and it was: " %)

(#(str "I take one argument and it was: " %)
  "foo")
