# Cypher Tool — My Notes (Abby)

Covers how to run it, how each cipher works, their weaknesses, and how
modern encryption compares.

## Files

```
Ciphers/
├── ROT13.java
├── Caesar.java
└── Atbash.java
```

## How to Run

1. Install Java JDK 8 or later.
2. From the project root, compile:
   ```bash
   javac Main.java CypherTool.java Ciphers/*.java InputValidator.java Cipher.java
   ```
3. Run:
   ```bash
   java Main
   ```

## How Each Cipher Works

### ROT13
Moves every letter 13 places forward in the alphabet, wrapping back to
A after Z. Since 13 + 13 = 26 (a full loop), doing it twice gets you
back to the original — that's why `decrypt()` just calls `encrypt()`
again.

```java
c = (char) (((c - 'A' + 13) % 26) + 'A');
```

- `c - 'A'` turns the letter into a number: A=0, B=1, … Z=25
- `+ 13` shifts it forward
- `% 26` wraps it back into range if it goes past Z
- `+ 'A'` turns the number back into a letter

### Atbash
Flips the alphabet: A↔Z, B↔Y, and so on. Doing it twice also gets you
back to the original.

```java
c = (char) ('Z' - (c - 'A'));
```

`c - 'A'` gives the letter's position (0–25). Subtracting that from
`'Z'` flips it to the matching letter from the other end.

### Caesar
Shifts every letter by a number you choose, instead of a fixed amount
like ROT13.

```java
c = (char) (((c - 'A' + shift) % 26 + 26) % 26 + 'A');
```

Same idea as ROT13, but with `shift` instead of `13`. The extra
`+ 26) % 26` handles negative shifts — without it, a negative shift
could produce a number outside the letter range. `decrypt()` just
calls `encrypt()` with the shift flipped negative, since shifting
backward undoes shifting forward.

## Weaknesses

**All three:**
- Only work on A–Z. Numbers, punctuation, and other alphabets pass
  through untouched.
- None of them are secure. They're simple substitution — someone can
  break them by hand.

**ROT13 & Atbash:** There's no key at all. Once you know which cipher
it is, it's already broken.

**Caesar:** Only 25 possible shift values, so guessing the right one
by trying each is fast.

## How Modern Ciphers Differ

These are all **classical ciphers** — good for learning, not for real
security. Modern encryption replaces them with:

- **AES** — uses a real secret key (not just a small shift number) and
  much more complex math to scramble the message
- **RSA** — uses two keys (public and private) instead of one shared
  shift value, so you can share the encryption key without giving away
  how to decrypt it
- **Hashing (e.g. SHA-256)** — used for things like passwords, where
  you don't want to reverse it at all, just check if it matches

The short version: Caesar/ROT13/Atbash *hide* text by rearranging
letters in a guessable way. Modern encryption *secures* text using
math that's practically impossible to reverse without the right key.
