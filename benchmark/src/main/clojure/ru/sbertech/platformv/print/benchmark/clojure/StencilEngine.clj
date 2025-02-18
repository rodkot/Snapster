(ns ru.sbertech.platformv.print.benchmark.clojure.StencilEngine
  (:require [stencil.api :as api]))

(defn process [template-path output-path data]
    (api/render! (api/prepare template-path) (into {} data) :output output-path))
