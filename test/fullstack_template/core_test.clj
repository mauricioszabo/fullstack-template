(ns fullstack-template.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [fullstack-template.core :as core]
            [matcher-combinators.test]
            [io.pedestal.http :as http]
            [io.pedestal.test :as http-test]
            [cheshire.core :as json]))

(defn make-request! [db verb path & args]
  (let [server (core/create-server (core/pedestal-config false db))
        service-fn (::http/service-fn server)]
    (apply http-test/response-for service-fn verb path args)))

(defn get! [path]
  (make-request! (atom {}) :get path))

(defn get-json!
  ([path] (get! (atom {}) path))
  ([db path]
   (update (make-request! db :get path) :body json/decode true)))

(deftest core-test
  (testing "redirects to HTML root"
    (is (match? {:status 302
                 :headers {"Location" "/index.html"}}
                (get! "/"))))

  (testing "renders index HTML"
    (is (match? {:status 200
                 :body #"<body>"}
                (get! "/index.html")))))
