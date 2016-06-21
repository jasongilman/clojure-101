(ns clojure-102.core
  "Demonstration of all things sequential in Clojure")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sequences

;; Sequences are a fundamental abstraction in Clojure. Many things in Clojure
;; constitute a sequence

;; Lists
;; Vectors
;; Strings
;; Maps
;; Sets
;; Many more things including java collections, io streams, etc.

;; The seq function can be used to return a sequence over a collection. Sequences
;; are printed like lists but they are not lists.

(seq "A string")
(seq [1 2 3 4])
(seq {:a 1 :b 2})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; nil can be treated as the empty sequence

(first nil)

;; Nil and the empty sequence are not equal though.
(not= nil [])

;; Seq on empty sequence returns nil
(seq [])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Lists

;; A literal list
'(1 2 3 4)

;; Lists are how code is writen in Clojure. This is clojure code but also a list
;; containing + (function), 2, 3
(+ 2 3)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vectors
[1 2 3 4]

;; Vectors are similar to arrays in other languages. The have fast access to indexes.
;; Using vectors in your Clojure code for literal lists of elements avoids
;; "parentheses overload" and makes it more readable.

;; Get the 3rd item (0 based indexing)
(nth [1 2 3 4] 2)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Strings
"This is a sequence of characters"

(first "This string")


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Maps - a sequence of key value pairs.

;; This returns the first key value pair
(first {:first-name "Jane"
        :last-name "Smith"
        :age 53})

;; IMPORTANT: Don't rely on maps maintaining a specific key order.

;; Sorted maps will keep keys in order by their natural ordering
(sorted-map 1 "one"
            2 "two"
            4 "four"
            3 "three")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sets

;; A unique set of items
#{1 2 3 4 5}

;; IMPORTANT: Don't rely on sets maintaining a specific value order.

;; Make a set from another sequence
(set [1 2 3 3 3 3])
(set "The bees knees")


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Functions creating sequences

;; range produces a sequence of numbers

(range 10)
(range 2 8)
(range 2 20 2)

;; repeat repeats a value a specified number of times
(repeat 5 "Hello!")

;; Iterate returns each value passed through a function
;;
(take 5 (iterate #(* % 2) 2))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Laziness

;; range, repeat, and iterate are all lazy. The produce values as needed.
;; This avoids unnecessary work and let's us do interesting things like producing
;; an infinite sequence of values

;; This is all the numbers 0 and larger. Evaluating it will hang the REPL while
;; it tries to print out infinity
(range)

;; Drop the first million numbers and take the first one after that
(first (drop 1000000 (range)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Functions over sequences

;; map is probably the most basic function over sequences. It applies a function
;; to all the values in an input sequence and returns the resulting values.

;; Think of it as mapping a function over a set of input values.

(map inc [1 2 3 4])


(defn add-two-and-to-str
  [n]
  (str (+ n 2)))

(map add-two-and-to-str [1 2 3 4])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Laziness and Side Effects

;; Laziness is present in many of the functions that work on sequences. You should
;; be aware of this and try to avoid expecting side effects in functions called
;; by lazy functions

;; Defines a set of values that are the result. The values aren't printed after
;; this is defined. map is lazy
(def values
  (map (fn [v]
         ;; A side effect here where we're printing the values.
         (println "Hi, I'm a side effect. " v)
         v)
       [1 2 3 4]))

;; doall and dorun are two functions that can force evaluation of a lazy sequence
;; with side effects that we want to happen.
(doall values)

;; mapv is a non-lazy varient of map that returns a vector. It's evaluated immediately.
(def values
  (mapv (fn [v]
          ;; A side effect here where we're printing the values.
          (println "Hi, I'm a side effecting again. " v)
          v)
        [1 2 3 4]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; filter returns a sequence of values matching a predicate. (A predicate is a
;; function returning a truthy value.

(filter even? (range 25))
(filter odd? (range 25))


;; Stringing functions together. What does this return?
(filter odd? (map #(* 2 %) (range 25)))

;; The thread last macro is a handy way to string functions together






;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; reduce is another basic function that works over sequences

(reduce + [1 2 3 4])

;; a  longer way to write the same thing
(reduce
 ;; A function taking the memo value and a value from the input sequence
 (fn [memo v]
   (+ memo v))
 ;; The initial memo value
 0
 ;; The input sequence
 [1 2 3 4])

;; Write a reduce equivalent to mapv

(defn my-map
  [f values]
  (reduce (fn [values v]
            (conj values (f v)))
          []
          values))

(my-map inc [1 2 3 4])


filter

for

mapcat


take
drop

;; Functions taking a sequence
some
first
rest
last


;; Immutability


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Transients

;; TODO

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Transducers

;; TODO
