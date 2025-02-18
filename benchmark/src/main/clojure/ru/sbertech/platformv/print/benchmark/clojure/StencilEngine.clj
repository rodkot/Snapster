(ns ru.sbertech.platformv.print.benchmark.clojure.StencilEngine
(:require [stencil.api :as api]
          [clojure.java.io :as io]))

(defn process [template-path output-path data]
  (let [template (slurp template-path)
        result (api/render! template data)]
    (spit output-path result)))
