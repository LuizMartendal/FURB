package RefazendoExercicios;

public class ArvoreBST<T extends Comparable<T>> extends ArvoreBinariaAbstract<T> {

    public ArvoreBST() {}

    public void inserir(T info) {
        if (this.vazia()) {
            super.setRaiz(new NoArvoreBST<>(info));
        }
        ((NoArvoreBST<T>) this.raiz).inserir(info);
    }

    public NoArvoreBST<T> buscar(T info) {
        if (vazia()) {
            return null;
        }
        return ((NoArvoreBST<T>) this.raiz).buscar(info);
    }

    public void retirar(T info) {
        NoArvoreBST<T> no = this.buscar(info);

        if (no != null) {
            this.retirarUmNo(no);
        }
    }

    public void retirarUmNo(NoArvoreBST<T> no) {
        NoArvoreBST<T> noPai = this.getPai(no);

        if (no.getGrau() == 0) {
            if (noPai == null) {
                this.setRaiz(null);
            } else {
                if (noPai.getNoDir() == no) {
                    noPai.setNoDir(null);
                } else {
                    noPai.setNoEsq(null);
                }
            }
        } else {
            if (no.getGrau() == 1) {
                NoArvoreBST<T> noFilho = no.getUnicoFilho();
                if (noPai == null) {
                    this.setRaiz(noFilho);
                } else {
                    if (noPai.getNoDir() == no) {
                        noPai.setNoDir(noFilho);
                    } else {
                        noPai.setNoEsq(noFilho);
                    }
                }
            } else {
                NoArvoreBST<T> noSucessor = this.getSucessor(no);
                T info = noSucessor.getInfo();
                this.retirarUmNo(noSucessor);
                no.setInfo(info);
            }
        }
    }

    public NoArvoreBST<T> getPai(NoArvoreBST<T> no) {
        if (vazia() || this.raiz == no) {
            return null;
        }

        NoArvoreBST<T> noPai = (NoArvoreBST<T>) this.raiz;

        while (noPai.getNoDir() != no && noPai.getNoEsq() != no) {
            if (noPai.getInfo().compareTo(no.getInfo()) > 0) {
                noPai = (NoArvoreBST<T>) noPai.getNoEsq();
            } else {
                noPai = (NoArvoreBST<T>) noPai.getNoDir();
            }
        }

        return noPai;
    }

    public NoArvoreBST<T> getSucessor(NoArvoreBST<T> no) {
        if (vazia() || this.getNoComMaiorElemento() == no) {
            return null;
        }

        if (no.getNoDir() != null) {
            NoArvoreBST<T> noSucessor = (NoArvoreBST<T>) no.getNoDir();

            while (noSucessor.getNoEsq() != null) {
                noSucessor = (NoArvoreBST<T>) noSucessor.getNoEsq();
            }

            return noSucessor;
        }

        NoArvoreBST<T> noPai = this.getPai(no);

        while (noPai.getNoDir() == no) {
            no = noPai;
            noPai = this.getPai(noPai);
        }

        return noPai;
    }

    public T getSucessor(T info) {
        if (vazia()) {
            return null;
        }
        NoArvoreBST<T> no = this.buscar(info);
        return this.getSucessor(no).getInfo();
    }

    public NoArvoreBST<T> getAntecessor(NoArvoreBST<T> no) {
        if (vazia() || this.getNoComMenorElemento() == no) {
            return null;
        }

        if (no.getNoEsq() != null) {
            NoArvoreBST<T> noAntecessor = (NoArvoreBST<T>) no.getNoEsq();

            while (noAntecessor.getNoDir() != null) {
                noAntecessor = (NoArvoreBST<T>) noAntecessor.getNoDir();
            }

            return noAntecessor;
        }

        NoArvoreBST<T> noPai = this.getPai(no);

        while (noPai.getNoEsq() == no) {
            no = noPai;
            noPai = this.getPai(noPai);
        }

        return noPai;
    }

    public T getAntecessor(T info) {
        if (vazia()) {
            return null;
        }
        return this.getAntecessor(this.buscar(info)).getInfo();
    }

    public NoArvoreBST<T> getNoComMaiorElemento() {
        if (vazia()) {
            return null;
        }

        NoArvoreBST<T> no = (NoArvoreBST<T>) this.raiz;

        while (no.getNoDir() != null) {
            no = (NoArvoreBST<T>) no.getNoDir();
        }

        return no;
    }

    public T getMaiorElemento() {
        if (vazia()) {
            return null;
        }
        return this.getNoComMaiorElemento().getInfo();
    }

    public NoArvoreBST<T> getNoComMenorElemento() {
        if (vazia()) {
            return null;
        }

        NoArvoreBST<T> no = (NoArvoreBST<T>) this.raiz;

        while (no.getNoEsq() != null) {
            no = (NoArvoreBST<T>) no.getNoEsq();
        }

        return no;
    }

    public T getMenorElemento() {
        if (vazia()) {
            return null;
        }

        return this.getNoComMenorElemento().getInfo();
    }

    public String toStringOrdered() {
        if (vazia()) {
            return "{}";
        }

        String str = "{ ";
        NoArvoreBST<T> no = this.getNoComMenorElemento();
        str += no.getInfo();

        while (this.getSucessor(no) != null) {
            no = this.getSucessor(no);
            str += ", " + no.getInfo();
        }

        return str += " }";
    }
}
