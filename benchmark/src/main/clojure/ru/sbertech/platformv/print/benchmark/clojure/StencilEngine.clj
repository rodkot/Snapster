(ns ru.sbertech.platformv.print.benchmark.clojure.StencilEngine
  (:require [stencil.api :as api]
            [clojure.java.io :as io]))

(defn process [template-path output-path data]
  (when (.exists (io/file output-path))
    (io/delete-file output-path))
  (api/render! (api/prepare template-path) (into {} data) :output output-path))