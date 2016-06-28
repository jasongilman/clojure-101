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

(defn contains-things?
  [values]
  (if (seq values)
    true
    false))

(contains-things? [1 2 3])
(contains-things? [])
(contains-things? nil)

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

;; IMPORTANT: Don't rely on maps maintaining a specific key order. Due to an
;; implementation detail they appear to keep their order when small but won't
;; past a certain size.

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
;; Equality

;; Lists and vectors of the same items are equal
(= '(1 2 3) [1 2 3])

;; Not true for sets
(not= #{1 2 3} [1 2 3])

;; Maps are not equal to pair
(not= {:a 1 :b 2} [[:a 1] [:b 1]])

;; Nil can be treated as an empty sequence
(empty? nil)

;; But nil is not equal to an empty sequence
(not= [] nil)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Core functions to manipulate sequences

;; First, second, and last work how you would expect
(first [1 2 3 4])
(second [1 2 3 4])
(last [1 2 3 4])

;; rest gives you everything but the first
(rest [1 2 3 4])

;; Take and drop discard or keep a subset of the items.
(take 3 [1 2 3 4 5])
(drop 3 [1 2 3 4 5])

;; nth goes to a specific index
(nth [1 2 3 4] 1)


;; conj adds onto a sequence in a type specific way.

;; vector appends to end
(conj [1 2 3] 4)
;; Lists add to the front
(conj '(1 2 3) 4)
;; Sets receive the new element
(conj #{1 2 3} 4)
;; But do the right thing in place of duplicates
(conj #{1 2 3} 3)

;; Maps add a new key value pair.
(conj {:b 2 :c 3} [:a 1])
;; This is the more idiomatic way
(assoc {:b 2 :c 2} :a 1)

;; Sets also have disj for removing elements
(disj #{1 2 3} 2)

;; into let's you pour one sequence into another
;; It's handles types in the same way conj works

(into [1 2 3] [:a :b :c])
(into '(1 2 3) [:a :b :c])

(into {:a 1} [[:b 2] [:c 3]])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Immutability

(def my-nums [1 2 3 4])

(conj my-nums 7)

;; My nums hasn't changed
my-nums


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
(take 5 (iterate #(* % 2) 2))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Laziness

;; range, repeat, and iterate are all lazy. The produce values as needed.
;; This avoids unnecessary work and let's us do interesting things like producing
;; an infinite sequence of values

;; This is all the numbers 0 and larger. Evaluating it will hang the REPL while
;; it tries to print out infinity
(range)

;; Drop the first million numbers and take the first three after that
(take 3 (drop 1000000 (range)))



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

(comment
 ;; Defines a set of values that are the result. The values aren't printed after
 ;; this is defined. map is lazy
 (def values
   (map (fn [v]
          ;; A side effect here where we're printing the values.
          (println "Hi, I'm a side effect. " v)
          v)
        (range 4)))

 ;; doall and dorun are two functions that can force evaluation of a lazy sequence
 ;; with side effects that we want to happen.
 (doall values)

 (comment
  (def more-values
    (map (fn [v]
           (println "Hi, I'm a side effect. " v)
           v)
         (range 50)))

  ;; What happens if we do this?
  ;; (Chunking causes it to print out more than 1)
  (take 1 more-values)
  (take 2 more-values)
  (drop 49 more-values))

 ;; mapv is a non-lazy varient of map that returns a vector. It's evaluated immediately.
 (def values
   (mapv (fn [v]
           ;; A side effect here where we're printing the values.
           (println "Hi, I'm a side effecting again. " v)
           v)
         (range 4))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; filter returns a sequence of values matching a predicate. (A predicate is a
;; function returning a truthy value.

(filter even? (range 25))
(filter odd? (range 25))

(defn divisible-by?
  [^long n ^long divisor]
  (= 0 (mod n divisor)))

;; Stringing functions together. What does this return?
(filter #(divisible-by? % 5) (map #(* 2 %) (range 25)))

;; The thread last macro is a handy way to string functions together.

(->> (range 25)
     (map #(* 2 %))
     (filter #(divisible-by? % 5)))

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

(my-map str [1 2 3 4])


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Creating list comprehensions with for

;; The first line are bindings. Binding name on the left and sequences on the right
(for [x [1 2 3 4]]
  ;; This is the body. It's evaluated and returned.
  (* 2 x))

;; The real power in for comes from it's ability to skip over multiple ranges

(def suits
  [:heart :spade :diamond :club])

(def card-numbers
  [:ace 2 3 4 5 6 7 8 9 10 :jack :queen :king])

(def cards
  "All of the playing cards"
  (for [suit suits
        num card-numbers]
    [num suit]))

(count cards)

;; for also supports :when and :let

(for [x (range 10)
      :when (even? x)
      :let [double-x (* 2 x)
            triple-x (* 3 x)]
      y [:a :b :c :d]]
  {:x x
   :double double-x
   :triple triple-x
   :y y})


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Transients

;; Clojure manipulation of persistent data structures is fast but it does come with
;; some overhead. Clojure transients keeps the code the same but internally uses
;; mutation to avoid the overheads.

;; You first take a persistent data structure and make a transient version of it.
(let [my-nums (transient [1 2 3])
      ;; then you manipulate it using similar functions that end with a !
      ;; The code still looks like it's the immutable version.
      my-nums (conj! my-nums 4)]
  ;; When you're done create persistent version of it with persistent!
  (persistent! my-nums))

(def a-bunch-of-nums
  "A vector of 10 million longs for testing"
  (into [] (range 10000000)))

(comment
 (def my-nums
   (time
    (persistent! (reduce (fn [my-nums v]
                           (conj! my-nums v))
                         (transient [])
                         a-bunch-of-nums)))))
(comment
 (def my-slow-nums
   (time
    (reduce (fn [my-nums v]
              (conj my-nums v))
            []
            a-bunch-of-nums))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Transducers

;; An example from before that was a thread last macro:

(->> (range 25)
     (map #(* 2 %))
     (map inc)
     (filter #(divisible-by? % 5)))

;; Which is really just a maro that rewrites it to this:

(filter #(divisible-by? % 5)
        (map inc
             (map #(* 2 %)
                  (range 25))))

;; What's happening here when this executes?

;; 1. range lazy sequence
;; 2. lazy sequence doubling each value
;; 3. lazy sequence of applying increment
;; 4. lazy sequence keeping only those divisible by 5.


;; These lazy sequences have an overhead.

;; Transducers are a way of building up these sets of sequence manipulations
;; that's more efficient and let's you compose them together before you actually
;; have the sequence

;; Creating a very simple transducer
(def incrementer-xducer
  (map inc))

;; Two different ways to use them. (See http://clojure.org/reference/transducers for more)

;; Lazy
(sequence incrementer-xducer [1 2 3])

;; Eager
(into [] incrementer-xducer [1 2 3])

;; Transducers can be composed

(def doubled-incremented-values-divisible-by-5
  (comp
   (map #(* 2 ^long %))
   (map inc)
   (filter #(divisible-by? % 5))))

(sequence doubled-incremented-values-divisible-by-5 (range 25))


;; Non-scientific performance test
(comment

 (time
  (->> a-bunch-of-nums
       (map #(* 2 ^long %))
       (map inc)
       (filter #(divisible-by? % 5))
       count))

 (time
  (->> a-bunch-of-nums
       (into [] doubled-incremented-values-divisible-by-5)
       count)))
