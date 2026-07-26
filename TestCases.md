# Cypher Tool — Test Cases

Based on the official assignment spec and the sample run shown in the
instructions. Validation-behavior tests come first (these are graded
requirements, not optional extras), then per-cipher transformation tests.

---

## Input Validation

### Operation menu (`1 = Encrypt`, `2 = Decrypt`)

| Input | Expected Behavior |
|---|---|
| `1` | Accepted → proceeds to cipher menu, Encrypt selected |
| `2` | Accepted → proceeds to cipher menu, Decrypt selected |
| `3` | Rejected — not a valid option, re-prompt with clear error |
| `0` | Rejected — re-prompt |
| `abc` | Rejected — non-numeric, re-prompt |
| `` (empty/whitespace) | Rejected — re-prompt |
| `exit` | Program quits immediately, no further prompts |

### Cipher menu (`1 = ROT13`, `2 = Atbash`, `3 = <your cipher>`)

| Input | Expected Behavior |
|---|---|
| `1` | Accepted → ROT13 selected |
| `2` | Accepted → Atbash selected |
| `3` | Accepted → your chosen cipher selected |
| `4` | Rejected — re-prompt |
| `exit` | Program quits immediately |

### Message input

| Input | Expected Behavior |
|---|---|
| `  Hello World  ` (leading/trailing spaces) | Trimmed to `Hello World` before processing |
| `` (empty string) | Rejected — re-prompt, "message cannot be empty" |
| `   ` (only whitespace) | Rejected after trim — re-prompt |
| `exit` | Program quits immediately |
| Any valid non-empty string after trimming | Accepted, proceeds to encryption/decryption |

### General
- Invalid input at **any** stage prints a clear error message and
  re-prompts at that same stage (doesn't restart the whole program)
- Typing `exit` at **any** prompt quits the program immediately, no
  matter how far into the flow the user is

---

## Official Example (from the spec)

```
Select operation: 1 (Encrypt)
Select cypher: 1 (ROT13)
Message: Hello, kood//!
Output: Uryyb, xbbq//!
```

Use this as your first smoke test — if your program doesn't reproduce
this exact output, something's off before you even get to edge cases.

---

## ROT13

Fixed shift of 13. Self-inverse: encrypt and decrypt produce the same
transformation (running it twice returns the original).

| Input | Expected Output |
|---|---|
| `Hello, kood//!` | `Uryyb, xbbq//!` (official example) |
| `Hello World` | `Uryyb Jbeyq` |
| `abc` | `nop` |
| `xyz` | `klm` |
| `ABC` | `NOP` |
| `XYZ` | `KLM` |
| `123` | `123` (unchanged — non-alphabetic) |
| `a1b2c3` | `n1o2p3` |
| `Uryyb Jbeyq` (decrypt) | `Hello World` |

---

## Atbash

Reverses the alphabet (A↔Z, B↔Y, …). Also self-inverse.

| Input | Expected Output |
|---|---|
| `Hello World` | `Svool Dliow` |
| `abc` | `zyx` |
| `xyz` | `cba` |
| `ABC` | `ZYX` |
| `XYZ` | `CBA` |
| `123` | `123` (unchanged) |
| `a1b2c3` | `z1y2x3` |
| `Svool Dliow` (decrypt) | `Hello World` |

---

## Caesar (chosen cipher)

Shifts each letter by `n` positions, wrapping around. Needs a shift
value — decide how it's provided (fixed constant, or prompt the user
for it) and document that choice.

| Input | Shift | Operation | Expected Output |
|---|---|---|---|
| `Hello World` | 3 | Encrypt | `Khoor Zruog` |
| `Khoor Zruog` | 3 | Decrypt | `Hello World` |
| `xyz` | 3 | Encrypt | `abc` (wrap-around) |
| `abc` | 3 | Decrypt | `xyz` (wrap-around) |
| `123` | 3 | Encrypt | `123` (unchanged) |
| `Hello` | 0 | Encrypt | `Hello` (no change) |
| `Hello` | 26 | Encrypt | `Hello` (full wrap = no change) |

---

## Cross-cipher checks (apply to all three)

| Case | Example | Expected |
|---|---|---|
| Case preserved | `HeLLo` | Each letter keeps its original case after transform |
| Punctuation/numbers untouched | `Hi! 123` | Only letters change |
| Round-trip | any message | `decrypt(encrypt(msg)) == msg` |
| Leading/trailing whitespace | `  test  ` | Trimmed before processing |
| Empty after trim | `   ` | Rejected with error, re-prompt |
