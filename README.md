# techml-dataset-cljs [![GitHub Actions status |clojure-quant/techml-dataset-cljs](https://github.com/clojure-quant/techml-dataset-cljs/workflows/CI/badge.svg)](https://github.com/clojure-quant/techml-dataset-cljs/actions?workflow=CI)[![Clojars Project](https://img.shields.io/clojars/v/io.github.clojure-quant/techml-dataset-cljs.svg)](https://clojars.org/io.github.clojure-quant/techml-dataset-cljs)


- adds tml-dataset in the browser
- save/load datasets in transit format
- serve datasets via ring-handler


```

# demo

```
  cd demo
  clj -X:webly:npm-install
  clj -X:webly:compile
  clj -X:webly:run

  OR
  cd demo
  clj -X:webly:run:watch

```


# test

tmlds files generated and served via resources
```
curl localhost:8080/r/small.json
```

tmlds files generated and served via resources
```
curl localhost:8080/r/small.json
```



  

