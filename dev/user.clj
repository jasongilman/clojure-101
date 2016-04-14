(ns user
  (:require [clojure.tools.namespace.repl :as tnr]
            clojure.repl
            ;; Used for proto repl atom plugin
            proto))

(defn start
  [])

(defn reset []
  (tnr/refresh :after 'user/start))
