all:
	(cd calc-core; make $(MFLAGS) all)
	(cd calc-textui; make $(MFLAGS) all)
	(cd calc-tests; make $(MFLAGS) all)

clean:
	(cd calc-core; make $(MFLAGS) clean)
	(cd calc-textui; make $(MFLAGS) clean)
	(cd calc-tests; make $(MFLAGS) clean)

install:
	(cd calc-core; make $(MFLAGS) install)
	(cd calc-textui; make $(MFLAGS) install)
	(cd calc-tests; make $(MFLAGS) install)
