package com.portifolio.pdv.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.portifolio.pdv.dto.ClienteDTO;
import com.portifolio.pdv.dto.ItemPedidoDTO;
import com.portifolio.pdv.dto.PedidoDTO;

@Service
public class PdfService {
    private PdfPCell cabecalho(String texto){
        Font fonte = FontFactory.getFont(FontFactory.HELVETICA_BOLD,10);
        PdfPCell cell = new PdfPCell(new Phrase(texto, fonte));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(5);
        return cell;
    }

    public byte[] gerarPdf(PedidoDTO dto){
        Long id = dto.getId();
        List<ItemPedidoDTO> itens = dto.getItens();
        ClienteDTO cliente = dto.getCliente();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4, 40, 40, 40, 40);
        PdfWriter.getInstance(document, baos);

        document.open();

        Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        Paragraph p = new Paragraph("PEDIDO ID: " + id, titulo);
        p.setAlignment(Element.ALIGN_CENTER);

        document.add(p);
        document.add(new Paragraph(""));

        Paragraph p2 = new Paragraph("EMPRESA: MATHEUS SR. LTDA", normal);
        p2.setAlignment(Element.ALIGN_CENTER);
        document.add(p2);
        // document.add(new Paragraph(""));

        Paragraph p3 = new Paragraph("CNPJ: XX.XXX.XXX/XXXX-XX --- CONTATO: +55 (77) 9 8115-8927", normal);
        p3.setAlignment(Element.ALIGN_CENTER);
        document.add(p3);

        Paragraph pp = new Paragraph("------------------------------------------------------------");
        pp.setAlignment(Element.ALIGN_CENTER);
        document.add(pp);


        Paragraph p4 = new Paragraph("CLIENTE: ID:" + cliente.getId() + " NOME: " + cliente.getNome(), normal);
        p4.setAlignment(Element.ALIGN_CENTER);
        document.add(p4);

        Paragraph p5 = new Paragraph("CPF: " + cliente.getCpf() + " Telefone: " + cliente.getTelefone());
        p5.setAlignment(Element.ALIGN_CENTER);
        document.add(p5);

        document.add(new Paragraph(""));
        
        // document.add(new LineSeparator());

        PdfPTable tabela = new PdfPTable(4);
        tabela.setWidthPercentage(100);
        tabela.setWidths(new float[]{ 4f, 2f, 2f, 1f});

        tabela.addCell(cabecalho("PRODUTO"));
        tabela.addCell(cabecalho("QUANTIDADE"));
        tabela.addCell(cabecalho("VALOR UNITÁRIO"));
        tabela.addCell(cabecalho("TOTAL"));

        for(ItemPedidoDTO item : itens){
            tabela.addCell(new Phrase(item.getProduto().getNome()));
            tabela.addCell(new Phrase(item.getQuantidade().toString()));
            tabela.addCell(new Phrase("R$ " + String.format("%.2f", item.getValorUnitario())));
            tabela.addCell(new Phrase("R$" + String.format("%.2f", item.getValorUnitario() * item.getQuantidade())));
        }
        
        tabela.setSpacingBefore(15f);
        tabela.setSpacingAfter(10f);

        document.add(tabela);

        Paragraph p6 = new Paragraph("TOTAL: R$ " + String.format("%.2f", dto.getTotal()), normal);
        p6.setAlignment(Element.ALIGN_CENTER);
        document.add(p6);

        document.add(new Paragraph(""));
        document.add(new Paragraph(""));

        Paragraph p7 = new Paragraph("OBRIGADO PELA PREFERÊNCIA! VOLTE SEMPRE.");
        p7.setAlignment(Element.ALIGN_CENTER);
        document.add(p7);

        document.close();
        
        return baos.toByteArray();

    }
}
