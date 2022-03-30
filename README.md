#Stream Router Service

##This dataflow realized abstract referral system.

Implementation of NATS [https://docs.nats.io/nats-concepts/subjects] [NATS subjects] data waterfall as described on proof_of_concept img.

<img src="proof_of_concept.png" width="800" alt="Data spread"/>


The nodes of unary tree is a NATS subject wired with user input (Producers).
Data always move from child to parent nodes with possibility filtering first level children individually. 



