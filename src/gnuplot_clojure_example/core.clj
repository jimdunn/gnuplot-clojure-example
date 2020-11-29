(ns gnuplot-clojure-example.core
  (:require [me.raynes.conch.low-level :as sh])
  (:gen-class))

(defn example-1 []
  (let [lines (sh/proc "feedgnuplot" "--stream" "trigger" "--domain" "--lines" "--xlen" "5")]
    (doseq [[x y] (partition 2 (interleave (range 20) (repeatedly #(rand-int 20))))]
      (sh/feed-from-string lines (str x " " y "\nreplot\n"))
      (Thread/sleep 500))))

(defn example-2 []
  (let [lines (sh/proc "feedgnuplot" "--stream" "trigger" "--domain" "--lines" "--xlen" "5")]
    (doseq [[x y1 y2] (map vector (range 20) (repeatedly #(rand-int 20)) (repeatedly #(rand-int 20)))]
      (sh/feed-from-string lines (str x " " y1 " " y2 "\nreplot\n"))
      (Thread/sleep 500))))

(defn example-3 []
  (let [circles (sh/proc "feedgnuplot" "--stream" "trigger" "--domain" "--circles" "--tuplesizeall" "3" "--title" "chachacha")]
    (doseq [[x y] (map vector (range 20) (repeatedly #(rand-int 20)))]
      (sh/feed-from-string circles (str x " " y " 0.5" "\nreplot\n"))
      (Thread/sleep 500))))

(defn -main [& args]
  (example-1)
  (example-2)
  (example-3))
