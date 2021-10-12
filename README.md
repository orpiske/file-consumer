Camel Java Router Project
=========================

Build
===

- Camel 3.11.2:

```shell
mvn -Pjmh,camel-3.11.2 clean verify
```

- Camel 3.13.0-SNAPSHOT:
```shell
mvn -Pjmh,camel-3.13.0-SNAPSHOT clean verify
```

Run (3.13.0-SNAPSHOT)
===
java -Dinput.dir=/path/to/data/set/tests/file-consumer/test-set/very-large -jar benchmarks-3.13.0-SNAPSHOT.jar



Prepare the data sets
===

Very Small (2500 files)

```shell
mkdir very-small && cd very-small
for dir in $(seq 1 5) ; do mkdir $dir && (cd $dir && (for file in $(seq 1 100) ; do echo "a" > $file ; done) ; cd ..) ; done
```

Small (25000 files)
```shell
mkdir small && cd small
for dir in $(seq 1 5) ; do mkdir $dir && (cd $dir && (for file in $(seq 1 5000) ; do echo "b" > $file ; done) ; cd ..) ; done
```


Medium (50000 files)
```shell
mkdir medium && cd medium
for dir in $(seq 1 5) ; do mkdir $dir && (cd $dir && (for file in $(seq 1 10000) ; do echo "d" > $file ; done) ; cd ..) ; done
```

Large (100000 files)
```shell
mkdir large && cd large
for dir in $(seq 1 10) ; do mkdir $dir && (cd $dir && (for file in $(seq 1 10000) ; do echo "e" > $file ; done) ; cd ..) ; done
```

Very Large (2000000 files)
```shell
mkdir very-large && cd very-large
for dir in $(seq 1 2) ; do mkdir $dir && (cd $dir && (for file in $(seq 1 1000000) ; do echo "f" > $file ; done) ; cd ..) ; done
```
