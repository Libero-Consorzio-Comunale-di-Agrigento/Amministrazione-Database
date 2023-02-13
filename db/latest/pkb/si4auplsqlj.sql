--liquibase formatted sql

--changeset mturra:201901301231_176 runOnChange:true stripComments:false

create or replace PACKAGE BODY SI4AUPLSQLJ wrapped
0
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
abcd
3
b
8106000
1
4
0
37
2 :e:
1PACKAGE:
1BODY:
1SI4AUPLSQLJ:
1FUNCTION:
1GETISSUERDN:
1CERTIFICATE:
1VARCHAR2:
1RETURN:
1LANGUAGE:
1JAVA:
1NAME:
1it.finmatica.login.DBLoginWrapper.getIssuerDN(java.lang.String) return java.l+
1ang.String:
1GETSUBJECTDN:
1it.finmatica.login.DBLoginWrapper.getSubjectDN(java.lang.String) return java.+
1lang.String:
1GETUSER:
1BLOB:
1it.finmatica.login.DBLoginWrapper.getUser(java.sql.Blob) return java.lang.Str+
1ing:
1VERSIONE:
1it.finmatica.login.DBLoginWrapper.versione() return java.lang.String:
1AUTHENTICATE:
1NUMBER:
1it.finmatica.login.DBLoginWrapper.authenticate(java.sql.Blob) return int:
1JDBCDRIVER:
1DATABASE:
1it.finmatica.login.DBLoginWrapper.authenticate(java.sql.Blob, java.lang.Strin+
1g, java.lang.String) return int:
1TOKEN:
1it.finmatica.login.DBLoginWrapper.authenticate(java.lang.String) return int:
1it.finmatica.login.DBLoginWrapper.authenticate(java.lang.String, java.lang.St+
1ring, java.lang.String) return int:
1USERNAME:
1PASSWORD:
1it.finmatica.login.DBLoginWrapper.authenticate(java.lang.String, java.lang.St+
1ring) return int:
1it.finmatica.login.DBLoginWrapper.authenticate(java.lang.String, java.lang.St+
1ring, java.lang.String, java.lang.String) return int:
1AUTHORIZE:
1MODULE:
1INSTANCE:
1CREDENTIALID:
1ACCESSTYPE:
1it.finmatica.login.DBLoginWrapper.authorize(java.lang.String, java.lang.Strin+
1g, java.lang.String, int, java.lang.String) return int:
1it.finmatica.login.DBLoginWrapper.authorize(java.lang.String, java.lang.Strin+
1g, java.lang.String, int, java.lang.String, java.lang.String, java.lang.Strin+
1g) return int:
1PROFILE:
1it.finmatica.login.DBLoginWrapper.authorize(java.lang.String, java.lang.Strin+
1g, java.lang.String, int, int, java.lang.String) return int:
1it.finmatica.login.DBLoginWrapper.authorize(java.lang.String, java.lang.Strin+
1g, java.lang.String, int, int, java.lang.String, java.lang.String, java.lang.+
1String) return int:
1GETAUTHENTICATIONTYPE:
1it.finmatica.login.DBLoginWrapper.getAuthenticationType(java.lang.String) ret+
1urn java.lang.String:
1it.finmatica.login.DBLoginWrapper.getAuthenticationType(java.lang.String, jav+
1a.lang.String, java.lang.String) return java.lang.String:
1it.finmatica.login.DBLoginWrapper.getUser(java.sql.Blob, java.lang.String, ja+
1va.lang.String) return java.lang.String:
1it.finmatica.login.DBLoginWrapper.getUser(java.lang.String) return java.lang.+
1String:
1it.finmatica.login.DBLoginWrapper.getUser(java.lang.String, java.lang.String,+
1 java.lang.String) return java.lang.String:
1SETATTRIBUTO:
1CONNECTIONURL:
1NOMINATIVO:
1ATTRIBUTO:
1VALORE:
1it.finmatica.login.LDAPLoginWrapper.setAttributo(java.lang.String, java.lang.+
1String, java.lang.String, java.lang.String, java.lang.String) return int:
1it.finmatica.login.LDAPLoginWrapper.setAttributo(java.lang.String, java.lang.+
1String, java.lang.String, java.lang.String) return int:
0
0
0
21c
2
0 a0 1d a0 97 a0 8d 8f
a0 b0 3d b4 :2 a0 2c 6a :3 a0
6e fe 68 a0 8d 8f a0 b0
3d b4 :2 a0 2c 6a :3 a0 6e fe
68 a0 8d 8f a0 b0 3d b4
:2 a0 2c 6a :3 a0 6e fe 68 a0
8d a0 b4 a0 2c 6a :3 a0 6e
fe 68 a0 8d 8f a0 b0 3d
b4 :2 a0 2c 6a :3 a0 6e fe 68
a0 8d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d b4 :2 a0
2c 6a :3 a0 6e fe 68 a0 8d
8f a0 b0 3d b4 :2 a0 2c 6a
:3 a0 6e fe 68 a0 8d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d b4 :2 a0 2c 6a :3 a0 6e
fe 68 a0 8d 8f a0 b0 3d
8f a0 b0 3d b4 :2 a0 2c 6a
:3 a0 6e fe 68 a0 8d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d b4 :2 a0
2c 6a :3 a0 6e fe 68 a0 8d
8f a0 b0 3d 8f a0 b0 3d
8f a0 b0 3d 8f a0 b0 3d
8f a0 b0 3d b4 :2 a0 2c 6a
:3 a0 6e fe 68 a0 8d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d b4 :2 a0 2c 6a :3 a0 6e
fe 68 a0 8d 8f a0 b0 3d
8f a0 b0 3d 8f a0 b0 3d
8f a0 b0 3d 8f a0 b0 3d
8f a0 b0 3d b4 :2 a0 2c 6a
:3 a0 6e fe 68 a0 8d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d b4 :2 a0
2c 6a :3 a0 6e fe 68 a0 8d
8f a0 b0 3d b4 :2 a0 2c 6a
:3 a0 6e fe 68 a0 8d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d b4 :2 a0 2c 6a :3 a0 6e
fe 68 a0 8d 8f a0 b0 3d
8f a0 b0 3d 8f a0 b0 3d
b4 :2 a0 2c 6a :3 a0 6e fe 68
a0 8d 8f a0 b0 3d b4 :2 a0
2c 6a :3 a0 6e fe 68 a0 8d
8f a0 b0 3d 8f a0 b0 3d
8f a0 b0 3d b4 :2 a0 2c 6a
:3 a0 6e fe 68 a0 8d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d 8f a0 b0 3d 8f a0
b0 3d b4 :2 a0 2c 6a :3 a0 6e
fe 68 a0 8d 8f a0 b0 3d
8f a0 b0 3d 8f a0 b0 3d
8f a0 b0 3d b4 :2 a0 2c 6a
:3 a0 6e fe 68 b1 b7 a4 11
a0 b1 56 4f 17 b5
21c
2
0 3 7 8 c 16 1a 33
2f 2e 3b 2b 40 44 48 4c
50 54 58 5c 61 6b 6f 73
8c 88 87 94 84 99 9d a1
a5 a9 ad b1 b5 ba c4 c8
cc e5 e1 e0 ed dd f2 f6
fa fe 102 106 10a 10e 113 11d
121 125 136 13a 13b 13f 143 147
14b 14f 153 158 162 166 16a 183
17f 17e 18b 17b 190 194 198 19c
1a0 1a4 1a8 1ac 1b1 1bb 1bf 1c3
1dc 1d8 1d7 1e4 1f1 1ed 1d4 1f9
202 1fe 1ec 20a 1e9 20f 213 217
21b 21f 223 227 22b 230 23a 23e
242 25b 257 256 263 253 268 26c
270 274 278 27c 280 284 289 293
297 29b 2b4 2b0 2af 2bc 2c9 2c5
2ac 2d1 2da 2d6 2c4 2e2 2c1 2e7
2eb 2ef 2f3 2f7 2fb 2ff 303 308
312 316 31a 333 32f 32e 33b 348
344 32b 350 343 355 359 35d 361
365 369 36d 371 376 380 384 388
3a1 39d 340 3a9 3b2 3ae 39c 3ba
3c7 3c3 399 3cf 3d8 3d4 3c2 3e0
3bf 3e5 3e9 3ed 3f1 3f5 3f9 3fd
401 406 410 414 418 431 42d 42c
439 446 442 429 44e 457 453 441
45f 46c 468 43e 474 47d 479 467
485 464 48a 48e 492 496 49a 49e
4a2 4a6 4ab 4b5 4b9 4bd 4d6 4d2
4d1 4de 4eb 4e7 4ce 4f3 4fc 4f8
4e6 504 511 50d 4e3 519 522 51e
50c 52a 537 533 509 53f 548 544
532 550 52f 555 559 55d 561 565
569 56d 571 576 580 584 588 5a1
59d 59c 5a9 5b6 5b2 599 5be 5c7
5c3 5b1 5cf 5dc 5d8 5ae 5e4 5ed
5e9 5d7 5f5 602 5fe 5d4 60a 5fd
60f 613 617 61b 61f 623 627 62b
630 63a 63e 642 65b 657 5fa 663
66c 668 656 674 681 67d 653 689
692 68e 67c 69a 6a7 6a3 679 6af
6b8 6b4 6a2 6c0 6cd 6c9 69f 6d5
6de 6da 6c8 6e6 6c5 6eb 6ef 6f3
6f7 6fb 6ff 703 707 70c 716 71a
71e 737 733 732 73f 72f 744 748
74c 750 754 758 75c 760 765 76f
773 777 790 78c 78b 798 7a5 7a1
788 7ad 7b6 7b2 7a0 7be 79d 7c3
7c7 7cb 7cf 7d3 7d7 7db 7df 7e4
7ee 7f2 7f6 80f 80b 80a 817 824
820 807 82c 835 831 81f 83d 81c
842 846 84a 84e 852 856 85a 85e
863 86d 871 875 88e 88a 889 896
886 89b 89f 8a3 8a7 8ab 8af 8b3
8b7 8bc 8c6 8ca 8ce 8e7 8e3 8e2
8ef 8fc 8f8 8df 904 90d 909 8f7
915 8f4 91a 91e 922 926 92a 92e
932 936 93b 945 949 94d 966 962
961 96e 97b 977 95e 983 98c 988
976 994 9a1 99d 973 9a9 9b2 9ae
99c 9ba 999 9bf 9c3 9c7 9cb 9cf
9d3 9d7 9db 9e0 9ea 9ee 9f2 a0b
a07 a06 a13 a20 a1c a03 a28 a31
a2d a1b a39 a46 a42 a18 a4e a41
a53 a57 a5b a5f a63 a67 a6b a6f
a74 a7e a3e a82 a84 a88 a94 a98
a9a a9d a9f aa8
21c
2
0 :2 1 9 e 2 b 17 26
:2 17 16 30 37 :2 2 43 4c :3 51
:2 2 b 18 27 :2 18 17 31 38
:2 2 44 4d :3 52 :2 2 b 13 22
:2 13 12 28 2f :2 2 3b 44 :3 49
:2 2 b 14 0 1b :2 2 27 30
:3 35 :2 2 b 18 27 :2 18 17 2d
34 :2 2 3e 47 :3 4c :2 2 b 18
27 :2 18 2d 3b :2 2d 45 51 :2 45
17 5b 62 :2 2 6c 75 :3 7a :2 2
b 18 21 :2 18 17 2b 32 :2 2
3c 45 :3 4a :2 2 b 18 21 :2 18
2b 39 :2 2b 43 4f :2 43 17 59
60 :2 2 6a 73 :3 78 :2 2 b 18
24 :2 18 2e 3a :2 2e 17 44 4b
:2 2 55 5e :3 63 :2 2 b 18 24
:2 18 2e 3a :2 2e 44 52 :2 44 5c
68 :2 5c 17 72 79 :2 2 83 8c
:3 91 :2 2 b 15 21 :2 15 2b 35
:2 2b 3f 4b :2 3f 55 65 :2 55 6d
7b :2 6d 14 85 8c :2 2 96 9f
:3 a4 :2 2 b 15 21 :2 15 2b 35
:2 2b 3f 4b :2 3f 55 65 :2 55 6d
7b :2 6d 85 91 :2 85 9b a9 :2 9b
14 b3 ba :2 2 c4 cd :3 d2 :2 2
b 15 21 :2 15 2b 35 :2 2b 3f
4b :2 3f 55 65 :2 55 6d 78 :2 6d
80 8e :2 80 14 98 9f :2 2 a9
b2 :3 b7 :2 2 b 15 21 :2 15 2b
35 :2 2b 3f 4b :2 3f 55 65 :2 55
6d 78 :2 6d 80 8e :2 80 98 a4
:2 98 ae bc :2 ae 14 c6 cd :2 2
d7 e0 :3 e5 :2 2 b 21 2d :2 21
20 37 3e :2 2 4a 53 :3 58 :2 2
b 21 2d :2 21 37 45 :2 37 4f
5b :2 4f 20 65 6c :2 2 78 81
:3 86 :2 2 b 13 22 :2 13 28 36
:2 28 40 4c :2 40 12 56 5d :2 2
69 72 :3 77 :2 2 b 13 22 :2 13
12 2c 33 :2 2 3f 48 :3 4d :2 2
b 13 1c :2 13 26 34 :2 26 3e
4a :2 3e 12 54 5b :2 2 67 70
:3 75 :2 2 b 18 29 :2 18 33 41
:2 33 4b 57 :2 4b 61 6e :2 61 78
82 :2 78 17 8c 93 :2 2 9d a6
:3 ab :2 2 b 18 26 :2 18 30 3c
:2 30 46 53 :2 46 5d 67 :2 5d 17
71 78 :2 2 82 8b :3 90 :5 2 5
:5 1
21c
2
0 :4 1 :11 2 :11 3 :11 4 :3 5 0 :9 5
:11 6 :19 7 :11 8 :19 9 :15 a :1d b :21 c :29 d
:25 e :2d f :11 10 :19 11 :19 12 :11 13 :19 14 :21 15
:21 16 17 :5 1
aaa
4
:3 0 1 :4 0 2
:3 0 3 :6 0 1
:2 0 4 :3 0 5
:a 0 15 2 :4 0
5 :2 0 3 7
:3 0 6 :7 0 9
8 :3 0 8 :3 0
7 :3 0 b d
0 15 6 e
:2 0 9 :3 0 a
:3 0 b :3 0 c
:4 0 13 :3 0 3
0 1000 15 :2 0
6 e 14 217
4 :3 0 d :a 0
26 3 :4 0 9
:2 0 :2 7 :3 0 6
:7 0 1a 19 :3 0
8 :3 0 7 :3 0
1c 1e 0 26
17 1f :2 0 9
:3 0 a :3 0 b
:3 0 e :4 0 24
:3 0 3 0 1000
26 :2 0 17 1f
25 217 4 :3 0
f :a 0 37 4
:4 0 d :2 0 b
10 :3 0 6 :7 0
2b 2a :3 0 8
:3 0 7 :3 0 2d
2f 0 37 28
30 :2 0 9 :3 0
a :3 0 b :3 0
11 :4 0 35 :3 0
3 0 1000 37
:2 0 28 30 36
217 4 :3 0 12
:a 0 44 5 :4 0
8 :4 0 7 :3 0
3b 3c 0 44
39 3d :2 0 9
:3 0 a :3 0 b
:3 0 13 :4 0 42
:3 0 3 0 1000
44 :2 0 39 3d
43 217 4 :3 0
14 :a 0 55 6
:4 0 11 :2 0 f
10 :3 0 6 :7 0
49 48 :3 0 8
:3 0 15 :3 0 4b
4d 0 55 46
4e :2 0 9 :3 0
a :3 0 b :3 0
16 :4 0 53 :3 0
3 0 1000 55
:2 0 46 4e 54
217 4 :3 0 14
:a 0 6e 7 :4 0
15 1e9 0 13
10 :3 0 6 :7 0
5a 59 :3 0 19
:2 0 17 7 :3 0
17 :7 0 5e 5d
:3 0 7 :3 0 18
:7 0 62 61 :3 0
8 :3 0 15 :3 0
64 66 0 6e
57 67 :2 0 9
:3 0 a :3 0 b
:3 0 19 :4 0 6c
:3 0 3 0 1000
6e :2 0 57 67
6d 217 4 :3 0
14 :a 0 7f 8
:4 0 1f :2 0 1d
7 :3 0 1a :7 0
73 72 :3 0 8
:3 0 15 :3 0 75
77 0 7f 70
78 :2 0 9 :3 0
a :3 0 b :3 0
1b :4 0 7d :3 0
3 0 1000 7f
:2 0 70 78 7e
217 4 :3 0 14
:a 0 98 9 :4 0
23 2c1 0 21
7 :3 0 1a :7 0
84 83 :3 0 27
:2 0 25 7 :3 0
17 :7 0 88 87
:3 0 7 :3 0 18
:7 0 8c 8b :3 0
8 :3 0 15 :3 0
8e 90 0 98
81 91 :2 0 9
:3 0 a :3 0 b
:3 0 1c :4 0 96
:3 0 3 0 1000
98 :2 0 81 91
97 217 4 :3 0
14 :a 0 ad a
:4 0 2d 340 0
2b 7 :3 0 1d
:7 0 9d 9c :3 0
32 399 0 2f
7 :3 0 1e :7 0
a1 a0 :3 0 8
:3 0 15 :3 0 a3
a5 0 ad 9a
a6 :2 0 9 :3 0
a :3 0 b :3 0
1f :4 0 ab :3 0
3 0 1000 ad
:2 0 9a a6 ac
217 4 :3 0 14
:a 0 ca b :4 0
36 3bf 0 34
7 :3 0 1d :7 0
b2 b1 :3 0 7
:3 0 1e :7 0 b6
b5 :3 0 3a :2 0
38 7 :3 0 17
:7 0 ba b9 :3 0
7 :3 0 18 :7 0
be bd :3 0 8
:3 0 15 :3 0 c0
c2 0 ca af
c3 :2 0 9 :3 0
a :3 0 b :3 0
20 :4 0 c8 :3 0
3 0 1000 ca
:2 0 af c3 c9
217 4 :3 0 21
:a 0 eb c :4 0
41 43e 0 3f
7 :3 0 1d :7 0
cf ce :3 0 45
464 0 43 7
:3 0 22 :7 0 d3
d2 :3 0 7 :3 0
23 :7 0 d7 d6
:3 0 49 :2 0 47
15 :3 0 24 :7 0
db da :3 0 7
:3 0 25 :7 0 df
de :3 0 8 :3 0
15 :3 0 e1 e3
0 eb cc e4
:2 0 9 :3 0 a
:3 0 b :3 0 26
:4 0 e9 :3 0 3
0 1000 eb :2 0
cc e4 ea 217
4 :3 0 21 :a 0
114 d :4 0 51
4e3 0 4f 7
:3 0 1d :7 0 f0
ef :3 0 55 509
0 53 7 :3 0
22 :7 0 f4 f3
:3 0 7 :3 0 23
:7 0 f8 f7 :3 0
59 52f 0 57
15 :3 0 24 :7 0
fc fb :3 0 7
:3 0 17 :7 0 100
ff :3 0 5d :2 0
5b 7 :3 0 18
:7 0 104 103 :3 0
7 :3 0 25 :7 0
108 107 :3 0 8
:3 0 15 :3 0 10a
10c 0 114 ed
10d :2 0 9 :3 0
a :3 0 b :3 0
27 :4 0 112 :3 0
3 0 1000 114
:2 0 ed 10d 113
217 4 :3 0 21
:a 0 139 e :4 0
67 5ae 0 65
7 :3 0 1d :7 0
119 118 :3 0 6b
5d4 0 69 7
:3 0 22 :7 0 11d
11c :3 0 7 :3 0
23 :7 0 121 120
:3 0 6f 5fa 0
6d 15 :3 0 24
:7 0 125 124 :3 0
15 :3 0 28 :7 0
129 128 :3 0 78
653 0 71 7
:3 0 25 :7 0 12d
12c :3 0 8 :3 0
15 :3 0 12f 131
0 139 116 132
:2 0 9 :3 0 a
:3 0 b :3 0 29
:4 0 137 :3 0 3
0 1000 139 :2 0
116 132 138 217
4 :3 0 21 :a 0
166 f :4 0 7c
679 0 7a 7
:3 0 1d :7 0 13e
13d :3 0 7 :3 0
22 :7 0 142 141
:3 0 80 69f 0
7e 7 :3 0 23
:7 0 146 145 :3 0
15 :3 0 24 :7 0
14a 149 :3 0 84
6c5 0 82 15
:3 0 28 :7 0 14e
14d :3 0 7 :3 0
17 :7 0 152 151
:3 0 88 :2 0 86
7 :3 0 18 :7 0
156 155 :3 0 7
:3 0 25 :7 0 15a
159 :3 0 8 :3 0
15 :3 0 15c 15e
0 166 13b 15f
:2 0 9 :3 0 a
:3 0 b :3 0 2a
:4 0 164 :3 0 3
0 1000 166 :2 0
13b 15f 165 217
4 :3 0 2b :a 0
177 10 :4 0 93
:2 0 91 7 :3 0
1d :7 0 16b 16a
:3 0 8 :3 0 7
:3 0 16d 16f 0
177 168 170 :2 0
9 :3 0 a :3 0
b :3 0 2c :4 0
175 :3 0 3 0
1000 177 :2 0 168
170 176 217 4
:3 0 2b :a 0 190
11 :4 0 97 79d
0 95 7 :3 0
1d :7 0 17c 17b
:3 0 9b :2 0 99
7 :3 0 17 :7 0
180 17f :3 0 7
:3 0 18 :7 0 184
183 :3 0 8 :3 0
7 :3 0 186 188
0 190 179 189
:2 0 9 :3 0 a
:3 0 b :3 0 2d
:4 0 18e :3 0 3
0 1000 190 :2 0
179 189 18f 217
4 :3 0 f :a 0
1a9 12 :4 0 a1
81c 0 9f 10
:3 0 6 :7 0 195
194 :3 0 a5 :2 0
a3 7 :3 0 17
:7 0 199 198 :3 0
7 :3 0 18 :7 0
19d 19c :3 0 8
:3 0 7 :3 0 19f
1a1 0 1a9 192
1a2 :2 0 9 :3 0
a :3 0 b :3 0
2e :4 0 1a7 :3 0
3 0 1000 1a9
:2 0 192 1a2 1a8
217 4 :3 0 f
:a 0 1ba 13 :4 0
ab :2 0 a9 7
:3 0 6 :7 0 1ae
1ad :3 0 8 :3 0
7 :3 0 1b0 1b2
0 1ba 1ab 1b3
:2 0 9 :3 0 a
:3 0 b :3 0 2f
:4 0 1b8 :3 0 3
0 1000 1ba :2 0
1ab 1b3 1b9 217
4 :3 0 f :a 0
1d3 14 :4 0 af
8f4 0 ad 7
:3 0 1a :7 0 1bf
1be :3 0 b3 :2 0
b1 7 :3 0 17
:7 0 1c3 1c2 :3 0
7 :3 0 18 :7 0
1c7 1c6 :3 0 8
:3 0 7 :3 0 1c9
1cb 0 1d3 1bc
1cc :2 0 9 :3 0
a :3 0 b :3 0
30 :4 0 1d1 :3 0
3 0 1000 1d3
:2 0 1bc 1cc 1d2
217 4 :3 0 31
:a 0 1f4 15 :4 0
b9 973 0 b7
7 :3 0 32 :7 0
1d8 1d7 :3 0 bd
999 0 bb 7
:3 0 33 :7 0 1dc
1db :3 0 7 :3 0
1e :7 0 1e0 1df
:3 0 c1 :2 0 bf
7 :3 0 34 :7 0
1e4 1e3 :3 0 7
:3 0 35 :7 0 1e8
1e7 :3 0 8 :3 0
15 :3 0 1ea 1ec
0 1f4 1d5 1ed
:2 0 9 :3 0 a
:3 0 b :3 0 36
:4 0 1f2 :3 0 3
0 1000 1f4 :2 0
1d5 1ed 1f3 217
4 :3 0 31 :a 0
211 16 :4 0 c9
a18 0 c7 7
:3 0 33 :7 0 1f9
1f8 :3 0 cd a3e
0 cb 7 :3 0
1e :7 0 1fd 1fc
:3 0 7 :3 0 34
:7 0 201 200 :4 0
215 0 cf 7
:3 0 35 :7 0 205
204 :3 0 8 :3 0
15 :3 0 207 209
0 211 1f6 20a
:2 0 9 :3 0 a
:3 0 b :3 0 37
:4 0 20f :3 0 3
0 1000 211 :2 0
1f6 20a 210 217
0 215 :3 0 215
217 213 214 :6 0
218 :2 0 3 :3 0
d4 0 4 215
21a :2 0 2 218
21b :8 0
ea
4
:3 0 1 7 1
a 1 18 1
1b 1 29 1
2c 1 47 1
4a 1 58 1
5c 1 60 3
5b 5f 63 1
71 1 74 1
82 1 86 1
8a 3 85 89
8d 1 9b 1
9f 2 9e a2
1 b0 1 b4
1 b8 1 bc
4 b3 b7 bb
bf 1 cd 1
d1 1 d5 1
d9 1 dd 5
d0 d4 d8 dc
e0 1 ee 1
f2 1 f6 1
fa 1 fe 1
102 1 106 7
f1 f5 f9 fd
101 105 109 1
117 1 11b 1
11f 1 123 1
127 1 12b 6
11a 11e 122 126
12a 12e 1 13c
1 140 1 144
1 148 1 14c
1 150 1 154
1 158 8 13f
143 147 14b 14f
153 157 15b 1
169 1 16c 1
17a 1 17e 1
182 3 17d 181
185 1 193 1
197 1 19b 3
196 19a 19e 1
1ac 1 1af 1
1bd 1 1c1 1
1c5 3 1c0 1c4
1c8 1 1d6 1
1da 1 1de 1
1e2 1 1e6 5
1d9 1dd 1e1 1e5
1e9 1 1f7 1
1fb 1 1ff 1
203 4 1fa 1fe
202 206 :2 15 26
37 44 55 6e
7f 98 ad ca
eb 114 139 166
177 190 1a9 1ba
1d3 1f4 211
1
4
0
21a
0
1
28
16
55
0 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
39 1 5
6 1 2
af 1 b
9a 1 a
81 1 9
70 1 8
57 1 7
46 1 6
1c5 14 0
19b 12 0
182 11 0
154 f 0
102 d 0
bc b 0
8a 9 0
60 7 0
17a 11 0
169 10 0
13c f 0
117 e 0
ee d 0
cd c 0
b0 b 0
9b a 0
1f6 1 16
1d5 1 15
1fb 16 0
1de 15 0
b4 b 0
9f a 0
1bc 1 14
1ab 1 13
192 1 12
28 1 4
4 0 1
140 f 0
11b e 0
f2 d 0
d1 c 0
148 f 0
123 e 0
fa d 0
d9 c 0
1f7 16 0
1da 15 0
203 16 0
1e6 15 0
1d6 15 0
13b 1 f
116 1 e
ed 1 d
cc 1 c
158 f 0
12b e 0
106 d 0
dd c 0
1c1 14 0
197 12 0
17e 11 0
150 f 0
fe d 0
b8 b 0
86 9 0
5c 7 0
144 f 0
11f e 0
f6 d 0
d5 c 0
1ff 16 0
1e2 15 0
14c f 0
127 e 0
17 1 3
179 1 11
168 1 10
1bd 14 0
82 9 0
71 8 0
1ac 13 0
193 12 0
58 7 0
47 6 0
29 4 0
18 3 0
7 2 0
0
/