# SkipList

Implementazione leggera e modulare di una Skip List (lista a salti). Questo repository contiene una versione generica in C++ (template) della struttura dati SkipList, utile per insiemi ordinati con operazioni di inserimento, ricerca e rimozione in tempo medio logaritmico.

Se vuoi che adatti questo README allo stile del codice presente (nomi dei file, API esatta, thread-safety, opzioni di allocazione), inviami i dettagli o i file e aggiorno il testo di conseguenza.

## Panoramica

- Linguaggio: C++ (implementazione template)
- Scopo: struttura dati ordinata con prestazioni attese O(log n) per ricerca, inserimento e cancellazione
- Caratteristiche:
  - Implementazione generica (SkipList<T, Comparator, RNG>)
  - Livelli variabili determinati casualmente (o mediante RNG fornito)
  - Supporto per find/contains, insert, erase, clear
  - Facile da integrare e testare

## Complessità (aspettata)
- Ricerca: O(log n) in media, O(n) nel caso pessimo
- Inserimento: O(log n) in media
- Cancellazione: O(log n) in media
- Spazio: O(n) (con fattore costante legato al numero medio di livelli)

## API di esempio (esempio d'uso in C++)

Esempio minimo d'uso (adattare ai nomi reali delle classi/metodi nel repo):

```cpp
#include "skiplist.h" // o il path reale

int main() {
    SkipList<int> sl; // usa il comparator di default (operator<)
    sl.insert(10);
    sl.insert(5);
    sl.insert(20);

    if (sl.contains(10)) {
        std::cout << "10 presente\n";
    }

    sl.erase(5);

    for (auto v : sl) { // se è fornito un iteratore
        std::cout << v << " ";
    }
    std::cout << "\n";
    return 0;
}
```

Adattare i nomi a quelli reali (es. SkipList<T>, contains, insert, erase, begin/end).

## Build & test

Esempio con CMake (se il progetto usa CMake):
```bash
mkdir -p build && cd build
cmake -DCMAKE_BUILD_TYPE=Release ..
cmake --build .
ctest --output-on-failure
```

Esempio compilazione diretta:
```bash
g++ -std=c++17 -O2 src/*.cpp -Iinclude -o bin/skiplist
./bin/skiplist
```

Esempio esecuzione di test manuale:
```bash
./bin/skiplist tests/sample_input.txt
```

Se nel repo sono presenti file di test (es. tests/), esegui quelli o aggiungi GoogleTest/Catch2 per test automatici.

## Thread-safety e varianti
- L'implementazione base non è thread-safe: sincronizzazione (mutex) necessaria per accessi concorrenti.
- Varianti possibili:
  - Allocatori custom per nodi
  - RNG deterministico per test (seed fissato)
  - Versione lock-free (più complessa)

## Benchmark e verifiche
- Confronta SkipList con std::set (RB-tree) e std::unordered_set per latenza e throughput.
- Misura con dataset crescenti e pattern di accesso (random, sequenziale).
- Usa valgrind per rilevare leak, and gprof/Perf per profilare.

## Contribuire
- Fork, branch feature, apri PR con descrizione chiara
- Aggiungi test per ogni nuova funzionalità
- Mantieni stile e convenzioni del progetto (C++17, commenti, nomi chiari)

## Licenza
Aggiungi qui la licenza scelta (es. MIT, Apache-2.0). Se vuoi, posso generare un file LICENSE con la licenza che preferisci.

## Contatti
Per bug, richieste o idee apri una Issue su GitHub: https://github.com/riccardoBuso5/skiplist/issues

Grazie per aver creato questo progetto — se mi invii il codice sorgente o i dettagli delle API, aggiorno subito il README con esempi precisi, snippet da includere e istruzioni di build testate.
