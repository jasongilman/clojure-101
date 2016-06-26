(ns clojure-102.sequence-tests
  "This contains problems to solve with Clojure sequences"
  (require [clojure.test :refer :all]
           [clojure.set :as set]
           [clojure.string :as str]))


(defn is-palindrome?
  "Returns a truthy value if the sequence is the same forwards and backwards.
   Empty sequences are not palindromes"
  [values]
  ;; TODO implement this
  nil)

(deftest is-palindrome-test
  (testing "vectors"
    (is (is-palindrome? [1 2 3 3 2 1]))
    (is (is-palindrome? [:a :b :c :d :c :b :a]))
    (is (not (is-palindrome? [1 2 3 3 2 1 1])))
    (is (not (is-palindrome? []))))
  (testing "strings"
   (is (is-palindrome? "foo oof"))
   (is (is-palindrome? "racecar"))
   (is (not (is-palindrome? "")))
   (is (not (is-palindrome? "race car")))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn count-matches
  "Returns the number of matches of an item in a sequence"
  [item values]
  ;; TODO implement this
  nil)

(deftest count-matches-test
  (is (= 3 (count-matches :a [:a 1 2 :a 3 4 5 6 :a])))
  (is (= 0 (count-matches :b [:a 1 2 :a 3 4 5 6 :a])))
  (is (= 2 (count-matches \a "onomatopoeia")))
  (is (= 0 (count-matches \b "onomatopoeia")))
  (is (= 0 (count-matches :a nil))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn compress
  "Compresses a sequence by returning tuples of repeating elements and how many
  times they were repeated.
  (compress [:a :a :a :b :c :c]) => ([3 :a] [1 :b] [2 :c])"
  [values]
  ;; TODO implement this
  nil)

(defn decompress
  "Decompresses a value returned by compress.
  (decompress [[3 :a] [1 :b] [2 :c]]) => (:a :a :a :b :c :c)"
  [values]
  ;; TODO implement this
  nil)

(deftest compression-test
  (testing "compress"
    (is (= [[3 :a] [1 :b] [2 :c]]
           (compress [:a :a :a :b :c :c])))

    (is (= [[1 :a] [1 :b] [1 :c]]
           (compress [:a :b :c])))

    (is (= [[5 :a]]
           (compress (repeat 5 :a)))))

  (testing "decompress"
    (is (= [:a :a :a :b :c :c]
           (decompress [[3 :a] [1 :b] [2 :c]])))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn my-flatten
  "Takes an arbitrarily nested set of sequences and returns a flattened sequence
   of values."
  [values]
  ;; TODO implement this
  ;; hint: look at the sequential? function
  nil)


(deftest flatten-test
  (testing "Nothing to flatten"
    (is (empty? (my-flatten nil)))
    (is (empty? (my-flatten [])))
    (is (= [1 2 3 4] (my-flatten [1 2 3 4]))))

  (testing "Basic flattening"
    (is (= [1 2 3 4 5 6]
           (my-flatten [1 [2 3] [4 5 6]]))))

  (testing "Arbitrarily nested flattening"
    (is (= [1 2 3 4 5 6 7 8]
           (my-flatten [1 [2 3 [4] 5] [[[6 7]]] 8])))))
