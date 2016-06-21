# Clojure 102 Notes

## Description

We'll cover the following topics:

* The difference between different sequential types and when to use them.
* How lazy sequences work and when laziness comes into play.
* Clojure's many functions for manipulating and creating sequences.
* More advanced topics such as Transients and Transducers.


## Notes from different sources

### Programming Clojure

* Everything can be thought of as a sequence
  * XML, database results, directory hierarchies, the contents of a file.
* Clojure has the `seq`. That's a logical list. Clojure doesn't tie the implementation details to the interface.




## Details

Outline

* The sequence abstraction
  * printed sequences look like lists. It doesn't actually mean they're lists though.
  * Few Types and many functions
* Sequential types Introduction
  * Strings
  * Lists
  * Vectors
  * Maps
    * sorted-map
  * Sets
    * sorted-set
  * Others?
    * io streams
    * java collections
    *
* Immutability
* Differences between the major types
* Functions that operate on sequences
  * first, rest, second, last,
  * take, map, reduce, filter,
  * cons, conj,
  * into - pouring a sequence into another one.
  * More here: http://clojure.org/reference/sequences
* Functions that create sequences
  * range, repeat, iterate, cycle
* Laziness
  * What is laziness and what is it used for?
  * for - and what that produces
    * Use cards (suits and then a sequence of numbers and )
  * Most of the functions like map are lazy.
    * Show side effects and how they don't occur until you consume the sequence.
  * Functions producing
  * lazy-seq - how lazy lists are built.
  * Libraries take advantage of it.
    * Read a giant file and pass it around without reading the whole thing.
    * Read CSV records without loading the whole thing.
    * Refer to every number.
  * Forcing side effects
    * doseq, doall, dorun
* Transients
  * Faster sequence manipulation.
* Transducers
  * Kind of like a recipe for building a sequence
  * Avoids overhead to laziness


Core sequence capabilities

* first
* rest
* cons - short for construct

Additional capabilities

* conj
* into



Everything is a seq

```Clojure
;; Strings
"abcdefghijklmnopqrstuvwxyz"

;; Lists
'(1 2 3 4 5)

;; Vectors
[1 2 3 4 5]

;; Maps (a sequence of key value pairs)
{:name "Jane", :age 53}

;; Sets
#{1 2 3 4 5}

```



TODO other stuff

* Difference between a List and a vector


lazy sequence example From Clojure Programming

```Clojure
(defn random-ints
  "Returns a lazy seq of random integers in the range [0,limit)."
  [limit]
  (lazy-seq
   (cons (rand-int limit)
         (random-ints limit))))


(defn random-ints2
  "Returns a lazy seq of random integers in the range [0,limit)."
  []
  (println "Running")
  (cons (rand-int 10)
        (lazy-seq
         (random-ints2))))

(take 12 (random-ints2))


(type (lazy-seq '(5)))

(defn forever-fives
  []
  (lazy-seq
   (cons 5 (forever-fives))))


(take 12 (forever-fives))

```
