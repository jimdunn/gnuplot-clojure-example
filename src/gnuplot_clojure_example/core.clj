(ns gnuplot-clojure-example.core
  (:require [me.raynes.conch.low-level :as sh])
  (:gen-class))

(defn example-1 []
  (let [lines (sh/proc "feedgnuplot" "--stream" "trigger" "--domain" "--lines" "--xlen" "5")]
    (doseq [x (range 20)] (sh/feed-from-string lines (str x " " (rand) "\nreplot\n"))
           (Thread/sleep 500))))


(defn example-2 []
  (let [lines (sh/proc "feedgnuplot" "--stream" "trigger" "--domain" "--lines" "--xlen" "5")]
    (doseq [x (range 20)]
      (sh/feed-from-string lines (str x " " (rand-int 10) " " (rand-int 10) "\nreplot\n"))
      (Thread/sleep 500))))

(defn example-3 []
  (let [circles (sh/proc "feedgnuplot" "--stream" "trigger" "--domain" "--circles" "--tuplesizeall" "3" "--title" "chachacha")]
    (doseq [x (range 10)]
      (sh/feed-from-string circles (str x " " (rand-int 10) " 0.5" "\nreplot\n"))
      (Thread/sleep 500))))

(defn -main [& args]
  (example-1)
  (example-2)
  (example-3))
