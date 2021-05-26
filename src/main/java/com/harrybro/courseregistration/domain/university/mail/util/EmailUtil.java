package com.harrybro.courseregistration.domain.university.mail.util;

import com.harrybro.courseregistration.domain.university.mail.domain.EmailSubject;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String toAddress, EmailSubject subject, String body) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(toAddress);
            helper.setSubject(subject.getSubject());
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    public String getEmailAuthMessage(String email, String authCode) {
        return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <div style=\"max-width:520px;margin:0 auto\">\n" +
                "                        <div\n" +
                "                            style=\"vertical-align:top;text-align:left;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px\">\n" +
                "                            <div style=\"padding-top:0px;padding-bottom:0px;vertical-align:top;text-align:center\"><a\n" +
                "                                    href=\"http://localhost\" target=\"_blank\"\n" +
                "                                    data-saferedirecturl=\"https://www.google.com/\"><img\n" +
                "                                        src=\"https://lh3.googleusercontent.com/B31vjstY6pQjtNBoNjbKecp_s9XNLiAwPrGLfSTvLF5BT-7yptQaHM2Z-_xUa2In9J5jisU-joqCksljQt32JnZVuv4HzDP15rTa8oAKBd9bnWzS7o07CyMALegl1ZbXyCRGmWmlTO9i26AT9ZNbbGki9mIB2-Yy42HqhWHxR2f-6BdaOWAag910k8Sj-9DiPH05So6uHcV5SIQwLccb5waBG6dZg7Ml6j58944pn0kTNw_jlbfJ7SlMChhU4D2AuXoOg9XRjXg-1L8FfaoZgOY4zutFy8IyOux1tA8-faTjTfp1HepTckYwwJ_3_V73rbwKEuDsXYrfXShxjyjfaK6Sy_Hlhqsm3steq43-LYZcmgZ5K8Qd8SAqy9ddnv4l6e1mHQ_uGwzx3IGoMOg7gTXTCQIXJbTi9yK39hf8gX8Dgu9WeWLrLpWdXs8cKbDMaN4mjRUfcGux0zENqlZGnzhiWDWejzH9wupzwDA1mlBZjG_G1yKiowZNJkdZhbF7U5lbExRE8oEu2AtVp1poyFyYAQKL8KfR36kXNMg0V5F6lWK_rZuFDP2UqAmf9qr-aDCT1i0N8EbmaJwzqhuJk8ZxdZXnOrQFChh7gr8hNMWXc6-Ws0arMsC2NVTrWx1PvF7A5tDx1W9_lVzOd87dLhhm7vvWqgVOxiYkZSFBVLAtAup5zMD3Z7pvSkVcAwdGkAH29oZ2Ry7Zre0ykdb78w=w232-h95-no?authuser=0\"\n" +
                "                                        height=\"80\" alt=\"MJU logo\" style=\"border:0\" class=\"CToWUd\"></a></div>\n" +
                "                            <hr style=\"margin-top:0px;margin-bottom:26px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <h2\n" +
                "                                style=\"margin-bottom:0;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:20px;font-weight:500;color:#172b4d;line-height:24px;margin-top:28px\">\n" +
                "                                <span>명지대학교</span> 회원가입 인증 메일</h2>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                안녕하세요.</p>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                회원가입을 완료하시려면 아래의 <b>이메일 인증</b>을 클릭하세요.</p>\n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\">\n" +
                "                                            <a style=\\\"text-decoration: none; font-size: 20px; color: #011627\\\"\n" +
                "                                                href='http://localhost/api/v1/email-auth?email=" + email + "&authCode=" + authCode + "'>이메일 인증</a>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <hr style=\"margin-top:24px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <div style=\"color:#707070;font-size:13px;line-height:19px;text-align:center;margin-top:10px\">\n" +
                "                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#ffffff\"\n" +
                "                                    align=\"center\">\n" +
                "                                    <tbody>\n" +
                "                                        <tr>\n" +
                "                                            <td valign=\"top\" align=\"center\"\n" +
                "                                                style=\"padding-top:10px;line-height:18px;text-align:center;font-weight:none;font-size:12px;color:#505f79\">\n" +
                "                                                <span>이 메시지는 명지대학교에서 전송되었습니다.</span><br></td>\n" +
                "                                        </tr>\n" +
                "                                        <tr valign=\"top\">\n" +
                "                                            <td align=\"center\" style=\"padding-top:15px;padding-bottom:30px\"><a\n" +
                "                                                    href=\"http://localhost\" target=\"_blank\"\n" +
                "                                                    data-saferedirecturl=\"https://www.google.com\"><img\n" +
                "                                                        src=\"https://lh3.googleusercontent.com/KBPC8AIY7fXaRxR1mGhM3hdHCUIG894fTXgvnzbV4jWrfUxt8ocfMZEJbPkKkTcMC0NYieAzj-T6CjXiI8_p_Fk-rN9RGyxW1ZUpcXfYiNJ3yXuk07XCv7ksut2-vaCeqYW7bRDpgNDaPIrwYZs8ONm3D4Daif7OYqxXrBwoYGlxo9Gn5ZUumTp9MoDYPSxgEBWFqirnrAfjbwbqapec0ACn-uX-M4atLXOmvj2v4LoaI5Rjodl-IQkOmkjMrMBMvRLteKmrrpbawYkt9bvBC0Srb2YgPLxqDCiQHeRsCKNJCDnBXFDF9CNH1NnsoNUzY9YSMVNOrSSZMcyiXosbBo7jz_u21B3O91S0wCSyyB9kAmCbVuqpHRdG2fJxc2o-B4XdHvKjya_kgny0gYVn9-3TtNKI_X0hz42B-VNVD53sxa8KaGoEmBEqZowGdG4jDnmDhrwpOVTFA1-p3uWmQ4f1mkJ5AFWBfUX9cnx-CicVuuJNnL4VUPJh_4BV_wZxAPLr3QWUih6gyDLQaZMztzAybsEFJv_Y9PomVF8zs2RZkuyh5hnp1f6AGYxi9PZmiCKkqxrpCQEWketWobXJw3gsOZILf_lN1qTYHc_nQYelLrzjF6uktwnYhL6vjxgF0jJIpEZJzzNiq2oeZdjyGbFsPGFc_AVHuNhn5jDXyoyk1hLK3ROFeUWEuCTLXWgtOYa_q_qwC7HN3-f6jQgNRQ=w97-h124-no?authuser=0\"\n" +
                "                                                        width=\"60\" border=\"0\" alt=\"Atlassian\"\n" +
                "                                                        style=\"display:block;color:#4c9ac9\" class=\"CToWUd\"></a></td>\n" +
                "                                        </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>";
    }

    public String getFindEmailMessage(String email) {
        return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <div style=\"max-width:520px;margin:0 auto\">\n" +
                "                        <div\n" +
                "                            style=\"vertical-align:top;text-align:left;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px\">\n" +
                "                            <div style=\"padding-top:0px;padding-bottom:0px;vertical-align:top;text-align:center\"><a\n" +
                "                                    href=\"https://www.google.com\" target=\"_blank\"\n" +
                "                                    data-saferedirecturl=\"https://www.google.com/\"><img\n" +
                "                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3c04Gv-k45cMqMbwSpHLtBOXanwyyU4NJ7CCEImXO7Ea5W27F0KzqWm7ClWu96vPF1F9e9VWs4VfS-zqMY5EOwvi1hySnsMPuDA7I-bmfO4k9wb0o3CrCWE0uzCTaHVW7H6nW2oXvo5Irn8J9Acn98=w1280-h720-no?authuser=4\"\n" +
                "                                        height=\"150\" alt=\"KATI logo\" style=\"border:0\" class=\"CToWUd\"></a></div>\n" +
                "                            <hr style=\"margin-top:0px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <h2\n" +
                "                                style=\"margin-bottom:0;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:20px;font-weight:500;color:#172b4d;line-height:24px;margin-top:28px\">\n" +
                "                                <span>KATI</span> 이메일 찾기 안내 메일</h2>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                안녕하세요.</p>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                이메일 찾기 안내 드립니다. 아래의 <b>이메일</b>을 통해 로그인해주세요.</p>\n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\">\n" +
                "                                            <span style=font-size: 20px; color: #011627>" + email + "</span>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <hr style=\"margin-top:24px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <div style=\"color:#707070;font-size:13px;line-height:19px;text-align:center;margin-top:10px\">\n" +
                "                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#ffffff\"\n" +
                "                                    align=\"center\">\n" +
                "                                    <tbody>\n" +
                "                                        <tr>\n" +
                "                                            <td valign=\"top\" align=\"center\"\n" +
                "                                                style=\"padding-top:10px;line-height:18px;text-align:center;font-weight:none;font-size:12px;color:#505f79\">\n" +
                "                                                <span>이 메시지는 KATI에서 전송되었습니다.</span><br></td>\n" +
                "                                        </tr>\n" +
                "                                        <tr valign=\"top\">\n" +
                "                                            <td align=\"center\" style=\"padding-top:15px;padding-bottom:30px\"><a\n" +
                "                                                    href=\"https://www.kati.com\" target=\"_blank\"\n" +
                "                                                    data-saferedirecturl=\"https://www.google.com\"><img\n" +
                "                                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3e3OsSdKKOWeKcrnQOrArwgoAGuAVg5ZUukTz-h7wXQTucQZDxKJPOuRRxPGZeV7XeQsljC9nJ7I9SlKaSmpKM5jmG1f-WKBEmbPbhauwheNbKVUaEPcRnLu0MWY--WgMZZbxjz2m3wO9WMO28UfT4=s1182-no?authuser=0\"\n" +
                "                                                        width=\"40\" border=\"0\" alt=\"Atlassian\"\n" +
                "                                                        style=\"display:block;color:#4c9ac9\" class=\"CToWUd\"></a></td>\n" +
                "                                        </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>";
    }

    public String getFindPasswordMessage(String email, String authCode) {
        return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <div style=\"max-width:520px;margin:0 auto\">\n" +
                "                        <div\n" +
                "                            style=\"vertical-align:top;text-align:left;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px\">\n" +
                "                            <div style=\"padding-top:0px;padding-bottom:0px;vertical-align:top;text-align:center\"><a\n" +
                "                                    href=\"https://www.google.com\" target=\"_blank\"\n" +
                "                                    data-saferedirecturl=\"https://www.google.com/\"><img\n" +
                "                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3c04Gv-k45cMqMbwSpHLtBOXanwyyU4NJ7CCEImXO7Ea5W27F0KzqWm7ClWu96vPF1F9e9VWs4VfS-zqMY5EOwvi1hySnsMPuDA7I-bmfO4k9wb0o3CrCWE0uzCTaHVW7H6nW2oXvo5Irn8J9Acn98=w1280-h720-no?authuser=4\"\n" +
                "                                        height=\"150\" alt=\"KATI logo\" style=\"border:0\" class=\"CToWUd\"></a></div>\n" +
                "                            <hr style=\"margin-top:0px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <h2\n" +
                "                                style=\"margin-bottom:0;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:20px;font-weight:500;color:#172b4d;line-height:24px;margin-top:28px\">\n" +
                "                                <span>KATI</span> 임시 비밀번호 안내 메일</h2>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                안녕하세요.</p>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                임시 비밀번호 안내 드립니다. 아래의 <b>임시 비밀번호</b>를 통해 로그인해주세요.</p>\n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\">\n" +
                "                                            <span style=font-size: 20px; color: #011627>" + authCode + "</span>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <hr style=\"margin-top:24px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <div style=\"color:#707070;font-size:13px;line-height:19px;text-align:center;margin-top:10px\">\n" +
                "                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#ffffff\"\n" +
                "                                    align=\"center\">\n" +
                "                                    <tbody>\n" +
                "                                        <tr>\n" +
                "                                            <td valign=\"top\" align=\"center\"\n" +
                "                                                style=\"padding-top:10px;line-height:18px;text-align:center;font-weight:none;font-size:12px;color:#505f79\">\n" +
                "                                                <span>이 메시지는 KATI에서 전송되었습니다.</span><br></td>\n" +
                "                                        </tr>\n" +
                "                                        <tr valign=\"top\">\n" +
                "                                            <td align=\"center\" style=\"padding-top:15px;padding-bottom:30px\"><a\n" +
                "                                                    href=\"https://www.kati.com\" target=\"_blank\"\n" +
                "                                                    data-saferedirecturl=\"https://www.google.com\"><img\n" +
                "                                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3e3OsSdKKOWeKcrnQOrArwgoAGuAVg5ZUukTz-h7wXQTucQZDxKJPOuRRxPGZeV7XeQsljC9nJ7I9SlKaSmpKM5jmG1f-WKBEmbPbhauwheNbKVUaEPcRnLu0MWY--WgMZZbxjz2m3wO9WMO28UfT4=s1182-no?authuser=0\"\n" +
                "                                                        width=\"40\" border=\"0\" alt=\"Atlassian\"\n" +
                "                                                        style=\"display:block;color:#4c9ac9\" class=\"CToWUd\"></a></td>\n" +
                "                                        </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>";
    }

}
