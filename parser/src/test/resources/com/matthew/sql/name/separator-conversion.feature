Feature: Can handle Separated Words

    Scenario Outline: Splitting Separated Words words

        Given a Separator converter using "<separator>"
        When the word "<word>" is split
        Then the returned array is <words>

    Examples: Using -
        | separator | word                                    | words                                                |
        | -         | hello                                   | hello                                                |
        | -         | hello-world                             | hello, world                                         |
        | -         | hello_world                             | hello_world                                          |
        | -         | hello/world                             | hello/world                                          |
        | -         | Hello-World                             | Hello, World                                         |
        | -         | Hello_World                             | Hello_World                                          |
        | -         | Hello/World                             | Hello/World                                          |
        | -         | a-bc-de-fg-hi-jk-lm-no-pq-rs-tu-vw-xy-z | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z |
        | -         | a-Bc-De-Fg-Hi-Jk-Lm-No-Pq-Rs-Tu-Vw-Xy-Z | a, Bc, De, Fg, Hi, Jk, Lm, No, Pq, Rs, Tu, Vw, Xy, Z |

    Examples: Using _
        | separator | word                                    | words                                                |
        | _         | hello                                   | hello                                                |
        | _         | hello_world                             | hello, world                                         |
        | _         | hello-world                             | hello-world                                          |
        | _         | hello/world                             | hello/world                                          |
        | _         | Hello_World                             | Hello, World                                         |
        | _         | Hello-World                             | Hello-World                                          |
        | _         | Hello/World                             | Hello/World                                          |
        | _         | a_bc_de_fg_hi_jk_lm_no_pq_rs_tu_vw_xy_z | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z |
        | _         | a_Bc_De_Fg_Hi_Jk_Lm_No_Pq_Rs_Tu_Vw_Xy_Z | a, Bc, De, Fg, Hi, Jk, Lm, No, Pq, Rs, Tu, Vw, Xy, Z |

    Examples: Using /
        | separator | word                                    | words                                                |
        | /         | hello                                   | hello                                                |
        | /         | hello/world                             | hello, world                                         |
        | /         | hello_world                             | hello_world                                          |
        | /         | hello-world                             | hello-world                                          |
        | /         | Hello/World                             | Hello, World                                         |
        | /         | Hello_World                             | Hello_World                                          |
        | /         | Hello-World                             | Hello-World                                          |
        | /         | a/bc/de/fg/hi/jk/lm/no/pq/rs/tu/vw/xy/z | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z |
        | /         | a/Bc/De/Fg/Hi/Jk/Lm/No/Pq/Rs/Tu/Vw/Xy/Z | a, Bc, De, Fg, Hi, Jk, Lm, No, Pq, Rs, Tu, Vw, Xy, Z |

    Scenario Outline: Joining Separated Words list

        Given a Separator converter using "<separator>"
        When the words <words> are joined
        Then the returned string is "<word>"

    Examples: Using -
        | separator | word                                    | words                                                |
        | -         | hello                                   | hello                                                |
        | -         | hello-world                             | hello, world                                         |
        | -         | hello_world                             | hello_world                                          |
        | -         | hello/world                             | hello/world                                          |
        | -         | Hello-World                             | Hello, World                                         |
        | -         | Hello_World                             | Hello_World                                          |
        | -         | Hello/World                             | Hello/World                                          |
        | -         | a-bc-de-fg-hi-jk-lm-no-pq-rs-tu-vw-xy-z | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z |
        | -         | a-Bc-De-Fg-Hi-Jk-Lm-No-Pq-Rs-Tu-Vw-Xy-Z | a, Bc, De, Fg, Hi, Jk, Lm, No, Pq, Rs, Tu, Vw, Xy, Z |

    Examples: Using _
        | separator | word                                    | words                                                |
        | _         | hello                                   | hello                                                |
        | _         | hello_world                             | hello, world                                         |
        | _         | hello-world                             | hello-world                                          |
        | _         | hello/world                             | hello/world                                          |
        | _         | Hello_World                             | Hello, World                                         |
        | _         | Hello-World                             | Hello-World                                          |
        | _         | Hello/World                             | Hello/World                                          |
        | _         | a_bc_de_fg_hi_jk_lm_no_pq_rs_tu_vw_xy_z | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z |
        | _         | a_Bc_De_Fg_Hi_Jk_Lm_No_Pq_Rs_Tu_Vw_Xy_Z | a, Bc, De, Fg, Hi, Jk, Lm, No, Pq, Rs, Tu, Vw, Xy, Z |

    Examples: Using /
        | separator | word                                    | words                                                |
        | /         | hello                                   | hello                                                |
        | /         | hello/world                             | hello, world                                         |
        | /         | hello_world                             | hello_world                                          |
        | /         | hello-world                             | hello-world                                          |
        | /         | Hello/World                             | Hello, World                                         |
        | /         | Hello_World                             | Hello_World                                          |
        | /         | Hello-World                             | Hello-World                                          |
        | /         | a/bc/de/fg/hi/jk/lm/no/pq/rs/tu/vw/xy/z | a, bc, de, fg, hi, jk, lm, no, pq, rs, tu, vw, xy, z |
        | /         | a/Bc/De/Fg/Hi/Jk/Lm/No/Pq/Rs/Tu/Vw/Xy/Z | a, Bc, De, Fg, Hi, Jk, Lm, No, Pq, Rs, Tu, Vw, Xy, Z |
